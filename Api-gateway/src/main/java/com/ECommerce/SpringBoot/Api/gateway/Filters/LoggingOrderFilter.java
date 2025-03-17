package com.ECommerce.SpringBoot.Api.gateway.Filters;


import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class LoggingOrderFilter extends AbstractGatewayFilterFactory<LoggingOrderFilter.config> {

    public LoggingOrderFilter() {
        super(config.class);
    }


    @Override
    public GatewayFilter apply(config config) {
        return ((exchange, chain) -> {
            log.info("Logging from PRE order filter : {}",exchange.getRequest().getURI());
            return chain.filter(exchange);
        });
    }

    public static class config{}
}
