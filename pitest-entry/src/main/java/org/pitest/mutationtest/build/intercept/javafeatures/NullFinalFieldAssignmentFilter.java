package org.pitest.mutationtest.build.intercept.javafeatures;

import org.pitest.bytecode.analysis.MethodTree;
import org.pitest.mutationtest.build.intercept.Region;
import org.pitest.mutationtest.build.intercept.RegionInterceptor;

import java.util.List;

public class NullFinalFieldAssignmentFilter extends RegionInterceptor {
    @Override
    protected List<Region> computeRegions(MethodTree method) {
        return List.of();
    }
}
