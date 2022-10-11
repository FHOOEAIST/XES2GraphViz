/*
 * Copyright (c) 2022 the original author or authors.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package science.aist.xestographviz;

import lombok.experimental.UtilityClass;
import science.aist.xes.model.AttributeDateType;
import science.aist.xes.model.AttributeStringType;
import science.aist.xes.model.EventType;

import java.util.Date;

/**
 * <p>Utility class that helps to extract attributes from {@link EventType}</p>
 *
 * @author Andreas Pointner
 * @since 1.0
 */
@UtilityClass
public class EventTypeHelper {
    /**
     * Extracts the concept name ouf of an event type
     *
     * @param eventType the event type where the concept name should be extracted
     * @return the value of the concept name
     */
    public String extractConceptNameFromEventType(EventType eventType) {
        return eventType.getStringOrDateOrInt().stream()
                .filter(at -> at.getKey().equals("concept:name"))
                .map(AttributeStringType.class::cast)
                .findFirst()
                .orElseThrow()
                .getValue();
    }

    /**
     * Extracts the timestamp ouf of an event type
     *
     * @param eventType the event type where the timestamp should be extracted
     * @return the value of the timestamp
     */
    public Date extractDateFromEventType(EventType eventType) {
        return eventType.getStringOrDateOrInt().stream()
                .filter(at -> at.getKey().equals("time:timestamp"))
                .map(AttributeDateType.class::cast)
                .findFirst()
                .orElseThrow()
                .getValue()
                .toGregorianCalendar()
                .getTime();
    }
}
