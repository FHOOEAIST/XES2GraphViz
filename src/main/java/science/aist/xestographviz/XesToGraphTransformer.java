/*
 * Copyright (c) 2022 the original author or authors.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package science.aist.xestographviz;

import science.aist.gtf.graph.Graph;
import science.aist.gtf.graph.builder.GraphBuilder;
import science.aist.gtf.graph.builder.impl.GraphBuilderImpl;
import science.aist.gtf.transformation.Transformer;
import science.aist.xes.model.EventType;
import science.aist.xes.model.LogType;
import science.aist.xes.model.TraceType;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>The transformation from the {@link LogType} into a {@link Graph}</p>
 *
 * @author Andreas Pointner
 * @since 1.0
 */
public class XesToGraphTransformer implements Transformer<LogType, Graph<EventType, Void>> {
    @Override
    public Graph<EventType, Void> applyTransformation(LogType log) {
        // Create a Graph Builder and use the concept name as key
        GraphBuilder<EventType, Void> graphBuilder = GraphBuilderImpl.create(EventTypeHelper::extractConceptNameFromEventType);

        for (TraceType traceType : log.getTrace()) {
            // Sort the list of event types according to their timestamp
            List<EventType> events = traceType.getEvent().stream()
                    .sorted(Comparator.comparing(EventTypeHelper::extractDateFromEventType))
                    .collect(Collectors.toList());

            events.forEach(graphBuilder::addVertex);

            // Create edges between sibling elements
            for (int i = 0; i < events.size() - 1; i++) {
                graphBuilder.from(events.get(i)).to(events.get(i + 1));
            }
        }

        return graphBuilder.toGraph();
    }


}
