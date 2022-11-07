/*
 * Copyright (c) 2022 the original author or authors.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package science.aist.xestographviz;

import science.aist.gtf.graph.Edge;
import science.aist.gtf.graph.Graph;
import science.aist.gtf.graph.impl.traversal.DepthFirstSearchTraversalStrategy;
import science.aist.gtf.transformation.Transformer;
import science.aist.jack.math.MathUtils;
import science.aist.jack.math.MinMax;
import science.aist.xes.model.EventType;

/**
 * <p>Transformation from a {@link Graph} of {@link EventType} into a GraphViz representation of a Directly-Follows
 * Graph</p>
 *
 * @author Andreas Pointner
 * @since 1.0
 */
public class GraphToDirectlyFollowsGraphGraphVizTransformer implements Transformer<Graph<EventType, Void>, String> {

    /**
     * The min width of the pen
     */
    private static final double MIN_Y = 1;

    /**
     * The max width of the pen
     */
    private static final double MAX_Y = 5;

    @Override
    public String applyTransformation(Graph<EventType, Void> graph) {
        graph.setVertexTraversalStrategy(new DepthFirstSearchTraversalStrategy<>(graph));
        StringBuilder sb = new StringBuilder();
        sb.append("digraph G {").append("\n")
                .append("  rankdir=LR;").append("\n")
                .append("  node [shape=box];").append("\n");

        MinMax<Double> doubleMinMax = MathUtils.minMax(graph.getEdges().stream().mapToDouble(Edge::getWeight).toArray());

        graph.traverseEdges(vertex -> sb.append("  ").append(vertex.getElement().hashCode())
                        .append("[label=\"")
                        .append(EventTypeHelper.extractConceptNameFromEventType(vertex.getElement()))
                        .append("\"];\n"),
                edge -> sb.append("  ").append(edge.getSource().getElement().hashCode())
                        .append(" -> ")
                        .append(edge.getTarget().getElement().hashCode())
                        .append("[label=\"")
                        .append(edge.getWeight())
                        .append("\", penwidth = ")
                        .append(((int) (calcPenWidth(edge.getWeight(), doubleMinMax.getMax(), doubleMinMax.getMin()) * 100)) / 100.0)
                        .append("];\n"));

        sb.append("}");

        return sb.toString();
    }

    private double calcPenWidth(double x, double maxX, double minX) {
        return Math.abs(minX - maxX) <= 0.001 ? MIN_Y : MIN_Y + (MAX_Y - MIN_Y) * ((x - minX) / (maxX - minX));
    }
}
