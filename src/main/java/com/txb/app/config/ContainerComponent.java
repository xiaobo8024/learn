package com.txb.app.config;

import com.txb.app.container.core.Container;
import org.springframework.stereotype.Component;

@Component
public class ContainerComponent {
    static {
            Container container = Container.getInstance();
            container.start();
    }
}
