package org.irmc.industrialrevival.api.elements.compounds;

import com.google.common.base.Preconditions;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.irmc.industrialrevival.api.elements.reaction.ReactCondition;
import org.irmc.industrialrevival.api.machines.process.Environment;
import org.irmc.industrialrevival.api.objects.CiFunction;
import org.irmc.industrialrevival.api.exceptions.UnknownChemicalCompoundException;
import org.irmc.industrialrevival.dock.IRDock;
import org.irmc.industrialrevival.utils.Debug;
import org.irmc.industrialrevival.utils.NumberUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Represents a chemical formula for chemical reactions.
 * <p>
 * This class serves as a formula decoder for chemical reactions, parsing chemical formulas
 * in the format "reactants===products" and managing the input and output compounds along
 * with their stoichiometric coefficients.
 * </p>
 * <p>
 * The formula format supports complex chemical reactions with multiple reactants and products,
 * and can include reaction conditions that affect the reaction process.
 * </p>
 *
 * @author balugaq
 * @see ChemicalCompound
 * @see ReactCondition
 */
@EqualsAndHashCode(exclude = {"id"})
@ToString(exclude = {"input", "output"})
@Data
public class ChemicalFormula {
    public static final Pattern NUMBER_PATTERN = Pattern.compile("^(\\d+)");
    private final int id;
    private @Nullable
    final ConditionSensor conditionSensor;
    private final @NotNull String rawFormula;
    private @NotNull Map<ChemicalCompound, Integer> input; // molar mass of each compound
    private @NotNull Map<ChemicalCompound, Integer> output; // molar mass of each compound
    private @NotNull Set<ReactCondition> conditions;

    /**
     * Creates a new ChemicalFormula with the specified ID and formula.
     *
     * @param id the unique identifier for this formula
     * @param formula the chemical formula string
     */
    public ChemicalFormula(int id, @NotNull String formula) {
        this(id, formula, new HashSet<>());
    }

    /**
     * Creates a new ChemicalFormula with the specified ID, formula, and single condition.
     *
     * @param id the unique identifier for this formula
     * @param formula the chemical formula string
     * @param condition the reaction condition
     */
    public ChemicalFormula(int id, @NotNull String formula, @NotNull ReactCondition condition) {
        this(id, formula, Set.of(condition), null);
    }

    /**
     * Creates a new ChemicalFormula with the specified ID, formula, and multiple conditions.
     *
     * @param id the unique identifier for this formula
     * @param formula the chemical formula string
     * @param conditions the reaction conditions
     */
    public ChemicalFormula(int id, @NotNull String formula, @NotNull ReactCondition... conditions) {
        this(id, formula, Arrays.stream(conditions).collect(Collectors.toSet()), null);
    }

    /**
     * Creates a new ChemicalFormula with the specified ID, formula, and set of conditions.
     *
     * @param id the unique identifier for this formula
     * @param formula the chemical formula string
     * @param conditions the set of reaction conditions
     */
    public ChemicalFormula(int id, @NotNull String formula, @NotNull Set<ReactCondition> conditions) {
        this(id, formula, conditions, null);
    }

    /**
     * Creates a new ChemicalFormula with the specified ID, formula, conditions, and condition sensor.
     * <p>
     * The formula should be in the format "reactants===products" where reactants and products
     * are separated by "===". Multiple compounds on each side should be separated by "+".
     * </p>
     * <p>
     * Examples:
     * <ul>
     *   <li>"Zn+H2SO4===ZnSO4+H2"</li>
     *   <li>"Fe2O3+3H2SO4===Fe2(SO4)_3+3H2O"</li>
     * </ul>
     * </p>
     *
     * @param id the unique identifier for this formula
     * @param formula the chemical formula string
     * @param conditions the set of reaction conditions
     * @param conditionSensor the condition sensor for dynamic condition evaluation
     */
    public ChemicalFormula(int id, @NotNull String formula, @NotNull Set<ReactCondition> conditions, @Nullable ConditionSensor conditionSensor) {
        Preconditions.checkNotNull(formula, "formula cannot be null");
        Preconditions.checkArgument(formula.contains("==="), "Invalid chemical formula: " + formula);
        Preconditions.checkArgument(formula.split("===").length == 2, "Invalid chemical formula: " + formula);
        Preconditions.checkNotNull(conditions, "conditions cannot be null");

        formula = formula.replaceAll(" ", "");
        String left = formula.split("===")[0];
        String right = formula.split("===")[1];

        String[] leftParts = left.split("\\+");
        String[] rightParts = right.split("\\+");

        this.id = id;
        this.rawFormula = formula;
        this.conditions = conditions;
        this.conditionSensor = conditionSensor;

        try {
            this.input = parseCompounds(leftParts);
            this.output = parseCompounds(rightParts);
        } catch (UnknownChemicalCompoundException e) {
            this.input = new HashMap<>();
            this.output = new HashMap<>();
            Debug.error(new UnknownChemicalCompoundException(formula, getId(), e));
            return;
        }

        register();
    }

    /**
     * Parses a chemical compound from a string.
     *
     * @param compoundName the name of the compound
     * @return the chemical compound or null if it is not found
     */
    @Nullable
    public static ChemicalCompound parseCompound(@NotNull String compoundName) {
        return ChemicalCompound.forName(compoundName);
    }

    /**
     * Parses a list of chemical compounds from a list of strings.
     * <p>
     * Each string can optionally start with a number representing the stoichiometric coefficient.
     * If no number is provided, the coefficient defaults to 1.
     * </p>
     *
     * @param parts the list of strings representing compounds
     * @return the map of chemical compounds and their stoichiometric coefficients
     * @throws UnknownChemicalCompoundException if any compound cannot be parsed
     */
    @NotNull
    public static Map<ChemicalCompound, Integer> parseCompounds(@NotNull String[] parts) {
        Map<ChemicalCompound, Integer> compounds = new LinkedHashMap<>();
        for (String part : parts) {
            // example: "Zn"
            // example: “3H2SO4”
            // if the part begins with a number, it is the count of the compound
            Matcher matcher = NUMBER_PATTERN.matcher(part);
            int count = 1;
            if (matcher.find()) {
                // part example: "3H2SO4"
                // begins with a number
                String number = matcher.group(1);
                count = Integer.parseInt(number);
                part = part.substring(number.length());
            }

            // example: "Zn"
            // example: "H2SO4"  (no count)
            ChemicalCompound compound = parseCompound(part);
            if (compound == null) {
                throw new UnknownChemicalCompoundException("Invalid chemical compound: " + part);
            }

            compounds.put(compound, count);
        }

        return compounds;
    }

    /**
     * Registers this chemical formula in the global registry.
     *
     * @return this ChemicalFormula instance for method chaining
     */
    public ChemicalFormula register() {
        IRDock.getPlugin().getRegistry().registerChemicalFormula(this);
        return this;
    }

    /**
     * Creates a human-readable representation of this chemical formula.
     *
     * @return a TextComponent representing the formula in human-readable format
     */
    public TextComponent humanize() {
        return humanize(false);
    }

    /**
     * Creates a human-readable representation of this chemical formula.
     *
     * @param hoverable whether the conditions should be hoverable in the display
     * @return a TextComponent representing the formula in human-readable format
     */
    public TextComponent humanize(boolean hoverable) {
        var builder = Component.text();
        builder.append(humanizePart(input));
        builder.append(humanizeConditions(hoverable));
        builder.append(humanizePart(output));
        return builder.build();
    }

    /**
     * Creates a human-readable representation of a part of the chemical formula.
     * <p>
     * This method formats the compounds with proper stoichiometric coefficients
     * and subscript formatting for chemical formulas.
     * </p>
     *
     * @param compounds the map of compounds and their coefficients to format
     * @return a TextComponent representing the compounds in human-readable format
     */
    public TextComponent humanizePart(Map<ChemicalCompound, Integer> compounds) {
        var builder = Component.text();
        int index = 1;
        for (var entry : compounds.entrySet()) {
            if (entry.getValue() != 1) {
                builder.append(Component.text(entry.getValue()));
            }

            builder.append(Component.text(NumberUtil.toSubscript(entry.getKey().getHumanizedName())));
            if (index != compounds.size()) {
                builder.append(Component.text("+"));
            }
            index++;
        }

        return builder.build();
    }

    public TextComponent humanizeConditions(boolean hoverable) {
        if (conditions.isEmpty() || conditions.stream().findFirst().orElse(ReactCondition.NONE).equals(ReactCondition.NONE)) {
            return Component.text("===");
        }

        if (hoverable) {
            return Component.empty()
                    .append(Component.text("==="))
                    .append(Component.translatable("chemistry.formula.conditions").hoverEvent(
                            humanizeConditions(false)
                    )).append(Component.text("==="));
        } else {
            var builder = Component.text();
            builder.append(Component.text("==="));
            for (var condition : getConditions()) {
                if (condition != null) {
                    builder.append(condition.humanize());
                }
            }
            builder.append(Component.text("==="));
            return builder.build();
        }
    }

    /**
     * Functional interface for sensing reaction conditions based on the environment.
     * <p>
     * This interface allows for dynamic evaluation of reaction conditions based on
     * the current environment and existing conditions.
     * </p>
     *
     * @author balugaq
     */
    @FunctionalInterface
    public interface ConditionSensor extends CiFunction<Environment, Set<ReactCondition>, Double, Double> {
        /**
         * Returns the maximum producing proportion based on the environment and conditions.
         *
         * @param environment the current environment
         * @param conditions the set of reaction conditions
         * @param current the current value
         * @return the maximum producing proportion
         */
        @Override
        Double apply(@NotNull Environment environment, @NotNull Set<ReactCondition> conditions, @NotNull Double current);
    }
}
