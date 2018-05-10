package yubzhu.controller;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.json.JSONArray;
import org.springframework.web.bind.annotation.*;
import yubzhu.configure.GodasConfig;
import yubzhu.service.*;

import java.io.*;

/**
 * Created by 朱宇斌 on 2018/5/6
 */

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/godas/mixing")
public class GodasMixingController {

    @GetMapping("/basicinfo")
    public String queryBasicInfo() {
        File file = new File(GodasConfig.GODAS_PATH + "/variables_info.txt");
        String string = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String tempString;
            while ((tempString = reader.readLine()) != null) {
                string += tempString + "\n";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return string;
    }

    @GetMapping("/3d/nonvector")
    public JSONArray query3dNonvector(@RequestParam(value = "time") String time,
                                      @RequestParam(value = "from_lat", defaultValue = "-74.5") float fromLat,
                                      @RequestParam(value = "to_lat", defaultValue = "64.5") float toLat,
                                      @RequestParam(value = "from_lon", defaultValue = "0.5") float fromLon,
                                      @RequestParam(value = "to_lon", defaultValue = "259.5") float toLon) {
        JSONArray jsonArrayThflx = new GodasController().queryTotalDownwardHeatFlux(time, fromLat, toLat, fromLon, toLon);
        JSONArray jsonArraySltfl = new GodasController().querySaltFlux(time, fromLat, toLat, fromLon, toLon);
        JSONArray jsonArraySshg = new GodasController().querySeaSurfaceHeight(time, fromLat, toLat, fromLon, toLon);
        JSONArray jsonArrayDbss_obil = new GodasController().queryGeometricDepthBelowSeaSurfaceIsothermalLayer(time, fromLat, toLat, fromLon, toLon);
        JSONArray jsonArrayDbss_obml = new GodasController().queryGeometricDepthBelowSeaSurfaceMixingLayer(time, fromLat, toLat, fromLon, toLon);
        JSONArray jsonArray3dNonVector = new JSONArray();
        for (int i = 0; i < jsonArrayThflx.length(); i += 1) {
            JSONArray jsonArray = new JSONArray();
            jsonArray.put(((JSONArray)jsonArrayThflx.get(i)).get(0));
            jsonArray.put(((JSONArray)jsonArrayThflx.get(i)).get(1));
            jsonArray.put(((JSONArray)jsonArrayThflx.get(i)).get(2));
            jsonArray.put(((JSONArray)jsonArraySltfl.get(i)).get(2));
            jsonArray.put(((JSONArray)jsonArraySshg.get(i)).get(2));
            jsonArray.put(((JSONArray)jsonArrayDbss_obil.get(i)).get(2));
            jsonArray.put(((JSONArray)jsonArrayDbss_obml.get(i)).get(2));
            if ((double)jsonArray.get(2) == 0 && (double)jsonArray.get(3) == 0 && (double)jsonArray.get(4) == 0 && (double)jsonArray.get(5) == 0 && (double)jsonArray.get(6) == 0) {
                continue;
            }
            jsonArray3dNonVector.put(jsonArray);
        }
        return jsonArray3dNonVector;
    }

    @GetMapping("4d/nonvector")
    public JSONArray query4dNonvector(@RequestParam(value = "time") String time,
                                      @RequestParam(value = "level") int level,
                                      @RequestParam(value = "from_lat", defaultValue = "-74.5") float fromLat,
                                      @RequestParam(value = "to_lat", defaultValue = "64.5") float toLat,
                                      @RequestParam(value = "from_lon", defaultValue = "0.5") float fromLon,
                                      @RequestParam(value = "to_lon", defaultValue = "259.5") float toLon) {
        JSONArray jsonArraySalt = new GodasController().querySalinity(time, level, fromLat, toLat, fromLon, toLon);
        JSONArray jsonArrayDzdt = new GodasController().queryGeometricVerticalVelocity(time, level, fromLat, toLat, fromLon, toLon);
        JSONArray jsonArrayPottmp = new GodasController().queryPotentialTemperature(time, level, fromLat, toLat, fromLon, toLon);
        JSONArray jsonArray4dNonVector = new JSONArray();
        for (int i = 0; i < jsonArraySalt.length(); i += 1) {
            JSONArray jsonArray = new JSONArray();
            jsonArray.put(((JSONArray)jsonArraySalt.get(i)).get(0));
            jsonArray.put(((JSONArray)jsonArraySalt.get(i)).get(1));
            jsonArray.put(((JSONArray)jsonArraySalt.get(i)).get(2));
            jsonArray.put(((JSONArray)jsonArrayDzdt.get(i)).get(2));
            jsonArray.put(((JSONArray)jsonArrayPottmp.get(i)).get(2));
            if ((double)jsonArray.get(2) == 0 && (double)jsonArray.get(3) == 0 && (double)jsonArray.get(4) == 0) {
                continue;
            }
            jsonArray4dNonVector.put(jsonArray);
        }
        return jsonArray4dNonVector;
    }
    
}
