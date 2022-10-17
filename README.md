# XES to GraphViz

An implementation, that transforms a given XES input into a Directly-Follows Graphs rendered by GraphViz.

## Getting Started

To use the project, simply include the maven dependency on the project.

```xml
<dependency>
    <groupId>science.aist</groupId>
    <artifactId>xes-to-graphviz</artifactId>
    <version>1.0.0</version>
    <scope>compile</scope> <!-- Note: this is default -->
</dependency>
```

This then enables you to convert a XES log into a graph representation, and then render the graph into a GraphViz
representation of a Directly-Follows Graphs.

```java
LogType log = new LogRepository().load(...).getValue();
Transformer<LogType, String> xes2graphViz = new XesToGraphTransformer().andThen(new GraphToDirectFollowerGraphGraphVizTransformer());
String res = xes2graphViz.applyTransformation(log);
```

## FAQ

If you have any questions, please checkout our [FAQ](https://fhooeaist.github.io/XES2GraphViz/faq.html) section.

## Contributing

**First make sure to read our [general contribution guidelines](https://fhooeaist.github.io/CONTRIBUTING.html).**
   
## License

Copyright (c) 2022 the original author or authors.
DO NOT ALTER OR REMOVE COPYRIGHT NOTICES.

This Source Code Form is subject to the terms of the Mozilla Public
License, v. 2.0. If a copy of the MPL was not distributed with this
file, You can obtain one at https://mozilla.org/MPL/2.0/.

## Research

If you are going to use this project as part of a research paper, we would ask you to reference this project by citing
it. 

[![DOI](https://zenodo.org/badge/548833449.svg)](https://zenodo.org/badge/latestdoi/548833449)
