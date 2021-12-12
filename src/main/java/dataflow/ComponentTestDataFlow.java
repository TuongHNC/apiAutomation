package dataflow;

import enumerations.dataflowenum.ComponentDataSet;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Mark the test method using this annotation if the test needs the data related to component test flow
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ComponentTestDataFlow {

    ComponentDataSet init();
}
