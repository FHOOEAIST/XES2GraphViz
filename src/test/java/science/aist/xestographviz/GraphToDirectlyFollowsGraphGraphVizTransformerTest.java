/*
 * Copyright (c) 2022 the original author or authors.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package science.aist.xestographviz;

import org.testng.Assert;
import org.testng.annotations.Test;
import science.aist.gtf.graph.Graph;
import science.aist.gtf.transformation.Transformer;
import science.aist.xes.model.EventType;
import science.aist.xes.model.LogType;
import science.aist.xes.model.impl.LogRepository;


/**
 * <p>Tests {@link GraphToDirectlyFollowsGraphGraphVizTransformer}</p>
 *
 * @author Andreas Pointner
 */
public class GraphToDirectlyFollowsGraphGraphVizTransformerTest {

    @Test
    public void testApplyTransformation() {
        // given
        LogType log = new LogRepository().load(getClass().getResourceAsStream("/log.xes")).getValue();
        Graph<EventType, Void> graph = new XesToGraphTransformer().applyTransformation(log);
        GraphToDirectlyFollowsGraphGraphVizTransformer graphToDirectlyFollowsGraphGraphVizTransformer = new GraphToDirectlyFollowsGraphGraphVizTransformer();

        // when
        String res = graphToDirectlyFollowsGraphGraphVizTransformer.applyTransformation(graph);

        // then
        Assert.assertNotNull(res);
        System.out.println(res);
    }

    @Test
    public void testPipeline() {
        // given
        LogType log = new LogRepository().load(getClass().getResourceAsStream("/log.xes")).getValue();
        Transformer<LogType, String> xes2graphViz = new XesToGraphTransformer().andThen(new GraphToDirectlyFollowsGraphGraphVizTransformer());

        // when
        String res = xes2graphViz.applyTransformation(log);

        // then
        Assert.assertNotNull(res);
        System.out.println(res);
    }
}