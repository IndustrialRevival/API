package org.irmc.industrialrevival.api.objects;

/**
 * Functional interface representing a function that accepts three arguments and produces a result.
 * This is similar to Function in Java but supports three arguments.
 *
 * @param <A> the type of the first argument
 * @param <B> the type of the second argument
 * @param <C> the type of the third argument
 * @param <R> the type of the result
 */
@FunctionalInterface
public interface CiFunction<A, B, C, R> {
    /**
     * Applies this function to the given arguments.
     *
     * @param a the first function argument
     * @param b the second function argument
     * @param c the third function argument
     * @return the function result
     */
    R apply(A a, B b, C c);
}