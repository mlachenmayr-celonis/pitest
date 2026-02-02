package org.pitest.mutationtest.build.intercept.javafeatures;

import org.junit.Test;
import org.pitest.bytecode.analysis.OpcodeMatchers;
import org.pitest.mutationtest.engine.gregor.mutators.NullMutateEverything;
import org.pitest.verifier.interceptors.InterceptorVerifier;
import org.pitest.verifier.interceptors.VerifierStart;

public class NullFinalFieldAssignmentFilterTest {
    NullFinalFieldAssignmentFilter underTest = new NullFinalFieldAssignmentFilter();
    InterceptorVerifier v = VerifierStart.forInterceptor(underTest)
            .usingMutator(new NullMutateEverything());

    @Test
    public void filtersNullAssignmentToFinalField() {
        v.forClass(HasSimpleFinalVariableNullAssignment.class)
                .forCodeMatching(OpcodeMatchers.PUTFIELD.asPredicate())
                .mutantsAreGenerated()
                .allMutantsAreFiltered()
                .verify();
    }

    @Test
    public void doesNotFilterNonFinalFieldAssignment() {
        v.forClass(HasSimpleNonFinalVariableNullAssignment.class)
                .forCodeMatching(OpcodeMatchers.PUTFIELD.asPredicate())
                .mutantsAreGenerated()
                .noMutantsAreFiltered()
                .verify();
    }
}

class HasSimpleFinalVariableNullAssignment {

    private final Integer member2 = null;

    public Integer call() {
        return this.member2;
    }
}

class HasSimpleNonFinalVariableNullAssignment {

    private Integer member2 = null;

    public Integer call() {
        return this.member2;
    }
}