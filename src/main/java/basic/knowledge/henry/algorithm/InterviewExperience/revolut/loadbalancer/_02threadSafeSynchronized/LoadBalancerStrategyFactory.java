package basic.knowledge.henry.algorithm.InterviewExperience.revolut.loadbalancer._02threadSafeSynchronized;

import java.util.HashMap;
import java.util.Map;

public class LoadBalancerStrategyFactory {
    private static final Map<String, ILoadbalancerStrategy> strategyRegistry = new HashMap<>();

    static {
        // Register default strategies
        registerStrategy("roundrobin", new RobinStrategy());
        registerStrategy("random", new RandomStrategy());
    }

    public static void registerStrategy(String strategyType, ILoadbalancerStrategy strategy) {
        strategyRegistry.put(strategyType.toLowerCase(), strategy);
    }

    public static ILoadbalancerStrategy createStrategy(String strategyType) {
        ILoadbalancerStrategy strategySupplier = strategyRegistry.get(strategyType.toLowerCase());
        if (strategySupplier != null) {
            return strategySupplier;
        }
        throw new IllegalArgumentException("Unknown strategy type: " + strategyType);
    }
}
