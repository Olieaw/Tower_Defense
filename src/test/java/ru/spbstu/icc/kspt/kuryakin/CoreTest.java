package ru.spbstu.icc.kspt.kuryakin;

import org.junit.Test;
import ru.spbstu.icc.kspt.kuryakin.Core.Map;
import ru.spbstu.icc.kspt.kuryakin.Core.Tower.TowerMap;

public class CoreTest {
    @Test
    public void TestAddTower(){
        Map map = new Map("firstLevel");
        TowerMap towerMap = new TowerMap();
        towerMap.AddTower(2, 2, 1);
    }
}
