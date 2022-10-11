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
import science.aist.xes.model.EventType;
import science.aist.xes.model.LogType;
import science.aist.xes.model.impl.LogRepository;

import javax.xml.bind.JAXBElement;


/**
 * <p>Tests {@link XesToGraphTransformer}</p>
 *
 * @author Andreas Pointner
 */
public class XesToGraphTransformerTest {

    @Test
    public void testApplyTransformation() {
        // given
        XesToGraphTransformer xesToGraphTransformer = new XesToGraphTransformer();
        LogRepository logRepository = new LogRepository();
        JAXBElement<LogType> load = logRepository.load(getClass().getResourceAsStream("/log.xes"));
        LogType log = load.getValue();

        // when
        Graph<EventType, Void> graph = xesToGraphTransformer.applyTransformation(log);

        // then
        Assert.assertNotNull(graph);
        Assert.assertEquals(graph.getVertices().size(), 9);
        Assert.assertEquals(graph.getEdges().size(), 13);
    }
}