import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Network {
    private List<BgpRouter> routers;
    private Map<String, BgpRouter> routerMap;

    public Network() {
        routers = new ArrayList<>();
        routerMap = new HashMap<>();
    }

    public void addRouter(BgpRouter router) {
        routers.add(router);
        routerMap.put(router.getId(), router);
    }

    public List<BgpRouter> getRouters() {
        return routers;
    }

    public BgpRouter getRouterById(String id) {
        return routerMap.get(id);
    }
}

