package com.mylab.cromero.service.domain;

import com.openpojo.reflection.filters.FilterPackageInfo;
import com.openpojo.validation.Validator;
import com.openpojo.validation.ValidatorBuilder;
import com.openpojo.validation.rule.impl.GetterMustExistRule;
import com.openpojo.validation.rule.impl.NoFieldShadowingRule;
import com.openpojo.validation.rule.impl.NoNestedClassRule;
import com.openpojo.validation.rule.impl.NoPrimitivesRule;
import com.openpojo.validation.rule.impl.NoPublicFieldsExceptStaticFinalRule;
import com.openpojo.validation.rule.impl.NoStaticExceptFinalRule;
import com.openpojo.validation.rule.impl.TestClassMustBeProperlyNamedRule;
import com.openpojo.validation.test.impl.DefaultValuesNullTester;
import com.openpojo.validation.test.impl.GetterTester;
import org.junit.Test;

public class DomainTest {


    // Configured for expectation, so we know when a class gets added or removed.
    private static final int EXPECTED_CLASS_COUNT = 1;

    // The package to test
    private static final String POJO_PACKAGE = "com.mylab.cromero.service.domain";

    @Test
    public void testPojoStructureAndBehavior() {
        Validator validator = ValidatorBuilder.create()
                .with(new GetterMustExistRule())
                // Add Testers to validate behaviour for POJO_PACKAGE
                // We don't want any primitives in our Pojos.
                .with(new NoPrimitivesRule())
                // Pojo's should stay simple, don't allow nested classes, anonymous or declared.
                .with(new NoNestedClassRule())
                // Static fields must be final
                .with(new NoStaticExceptFinalRule())
                // Don't shadow parent's field names.
                .with(new NoFieldShadowingRule())
                // What about public fields, use one of the following rules
                // allow them only if they are static and final.
                .with(new NoPublicFieldsExceptStaticFinalRule())
                // Or you can be more restrictive and not allow ANY public fields in a Pojo.
                // pojoValidator.addRule(new NoPublicFieldsRule());
                // Make sure your tests are properly named
                .with(new TestClassMustBeProperlyNamedRule())

                /****
                 * Create Testers to validate the behavior of the classes at runtime.
                 ****/
                // Make sure our setters and getters are behaving as expected.
                .with(new GetterTester())

                // We don't want any default values to any fields - unless they are declared final or are primitive.
                .with(new DefaultValuesNullTester())
                .build();

        validator.validate(POJO_PACKAGE, new FilterPackageInfo());
    }







}
