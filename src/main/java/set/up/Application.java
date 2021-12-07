package set.up;

import set.up.db.Db;
import set.up.service.MainService;

public class Application {
    static {
        Db.init();
    }

    public static void main(String[] args) {
        MainService.mainService();
    }
}
