package org.irmc.industrialrevival.api.display.builder;

import lombok.Getter;
import org.bukkit.util.Transformation;
import org.jetbrains.annotations.NotNull;
import org.joml.Quaternionf;
import org.joml.Vector3f;

/**
 * Builder class for creating Transformation objects used by display entities.
 * <p>
 * This class provides a fluent API for building Transformation objects with
 * translation, rotation, and scale components. It allows precise control over
 * how display entities are positioned and oriented in 3D space.
 * </p>
 * <p>
 * The transformation consists of four components:
 * - Translation: The position offset
 * - Left Rotation: The first rotation applied
 * - Scale: The size scaling factors
 * - Right Rotation: The second rotation applied
 * </p>
 *
 * @author balugaq
 */
@Getter
public class TransformationBuilder {
    private final Vector3f translation = new Vector3f();
    private final Quaternionf leftRotation = new Quaternionf();
    private final Vector3f scale = new Vector3f(1, 1, 1);
    private final Quaternionf rightRotation = new Quaternionf();

    // Translation setters
    /**
     * Sets the X component of the translation vector.
     *
     * @param x the X translation value
     * @return this builder instance
     */
    public @NotNull TransformationBuilder setTranslationX(float x) {
        this.translation.x = x;
        return this;
    }

    /**
     * Sets the Y component of the translation vector.
     *
     * @param y the Y translation value
     * @return this builder instance
     */
    public @NotNull TransformationBuilder setTranslationY(float y) {
        this.translation.y = y;
        return this;
    }

    /**
     * Sets the Z component of the translation vector.
     *
     * @param z the Z translation value
     * @return this builder instance
     */
    public @NotNull TransformationBuilder setTranslationZ(float z) {
        this.translation.z = z;
        return this;
    }

    // Left Rotation setters
    /**
     * Sets the X component of the left rotation quaternion.
     *
     * @param x the X rotation value
     * @return this builder instance
     */
    public @NotNull TransformationBuilder setLeftRotationX(float x) {
        this.leftRotation.x = x;
        return this;
    }

    /**
     * Sets the Y component of the left rotation quaternion.
     *
     * @param y the Y rotation value
     * @return this builder instance
     */
    public @NotNull TransformationBuilder setLeftRotationY(float y) {
        this.leftRotation.y = y;
        return this;
    }

    /**
     * Sets the Z component of the left rotation quaternion.
     *
     * @param z the Z rotation value
     * @return this builder instance
     */
    public @NotNull TransformationBuilder setLeftRotationZ(float z) {
        this.leftRotation.z = z;
        return this;
    }

    /**
     * Sets the W component of the left rotation quaternion.
     *
     * @param w the W rotation value
     * @return this builder instance
     */
    public @NotNull TransformationBuilder setLeftRotationW(float w) {
        this.leftRotation.w = w;
        return this;
    }

    // Scale setters
    /**
     * Sets the X component of the scale vector.
     *
     * @param x the X scale value
     * @return this builder instance
     */
    public @NotNull TransformationBuilder setScaleX(float x) {
        this.scale.x = x;
        return this;
    }

    /**
     * Sets the Y component of the scale vector.
     *
     * @param y the Y scale value
     * @return this builder instance
     */
    public @NotNull TransformationBuilder setScaleY(float y) {
        this.scale.y = y;
        return this;
    }

    /**
     * Sets the Z component of the scale vector.
     *
     * @param z the Z scale value
     * @return this builder instance
     */
    public @NotNull TransformationBuilder setScaleZ(float z) {
        this.scale.z = z;
        return this;
    }

    // Right Rotation setters
    /**
     * Sets the X component of the right rotation quaternion.
     *
     * @param x the X rotation value
     * @return this builder instance
     */
    public @NotNull TransformationBuilder setRightRotationX(float x) {
        this.rightRotation.x = x;
        return this;
    }

    /**
     * Sets the Y component of the right rotation quaternion.
     *
     * @param y the Y rotation value
     * @return this builder instance
     */
    public @NotNull TransformationBuilder setRightRotationY(float y) {
        this.rightRotation.y = y;
        return this;
    }

    /**
     * Sets the Z component of the right rotation quaternion.
     *
     * @param z the Z rotation value
     * @return this builder instance
     */
    public @NotNull TransformationBuilder setRightRotationZ(float z) {
        this.rightRotation.z = z;
        return this;
    }

    /**
     * Sets the W component of the right rotation quaternion.
     *
     * @param w the W rotation value
     * @return this builder instance
     */
    public @NotNull TransformationBuilder setRightRotationW(float w) {
        this.rightRotation.w = w;
        return this;
    }

    /**
     * Builds a Transformation object with the configured components.
     *
     * @return the created Transformation object
     */
    public @NotNull Transformation build() {
        return new Transformation(translation, leftRotation, scale, rightRotation);
    }
}
