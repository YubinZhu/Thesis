package yubzhu.controller;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.json.JSONArray;
import org.springframework.web.bind.annotation.*;
import yubzhu.configure.GodasConfig;
import yubzhu.service.*;

/**
 * Created by 朱宇斌 on 2018/4/21
 */

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/godas")
public class GodasController {

    @GetMapping("/thflx")
    public JSONArray queryTotalDownwardHeatFlux(@RequestParam(value = "time") String time,
                                                @RequestParam(value = "from_lat", defaultValue = "-74.5") float fromLat,
                                                @RequestParam(value = "to_lat", defaultValue = "64.5") float toLat,
                                                @RequestParam(value = "from_lon", defaultValue = "0.5") float fromLon,
                                                @RequestParam(value = "to_lon", defaultValue = "259.5") float toLon) {
        DateTime dateTime = DateTime.parse(time, DateTimeFormat.forPattern("yyyy-MM"));
        int timeIndex = (dateTime.getYear() - GodasConfig.START_TIME.getYear()) * 12 + dateTime.getMonthOfYear() - GodasConfig.START_TIME.getMonthOfYear();
        return new TotalDownwardHeatFlux().getThflx(timeIndex, fromLat, toLat, fromLon, toLon);
    }


    @GetMapping("/mmtflx")
    public JSONArray queryMomentumFlux(@RequestParam(value = "time") String time,
                                       @RequestParam(value = "from_lat", defaultValue = "-74.5") float fromLat,
                                       @RequestParam(value = "to_lat", defaultValue = "64.5") float toLat,
                                       @RequestParam(value = "from_lon", defaultValue = "0.5") float fromLon,
                                       @RequestParam(value = "to_lon", defaultValue = "259.5") float toLon) {
        DateTime dateTime = DateTime.parse(time, DateTimeFormat.forPattern("yyyy-MM"));
        int timeIndex = (dateTime.getYear() - GodasConfig.START_TIME.getYear()) * 12 + dateTime.getMonthOfYear() - GodasConfig.START_TIME.getMonthOfYear();
        return new MomentumFlux().getMmtflx(timeIndex, fromLat, toLat, fromLon, toLon);
    }

    @GetMapping("/sltfl")
    public JSONArray querySaltFlux(@RequestParam(value = "time") String time,
                                   @RequestParam(value = "from_lat", defaultValue = "-74.5") float fromLat,
                                   @RequestParam(value = "to_lat", defaultValue = "64.5") float toLat,
                                   @RequestParam(value = "from_lon", defaultValue = "0.5") float fromLon,
                                   @RequestParam(value = "to_lon", defaultValue = "259.5") float toLon) {
        DateTime dateTime = DateTime.parse(time, DateTimeFormat.forPattern("yyyy-MM"));
        int timeIndex = (dateTime.getYear() - GodasConfig.START_TIME.getYear()) * 12 + dateTime.getMonthOfYear() - GodasConfig.START_TIME.getMonthOfYear();
        return new SaltFlux().getSltfl(timeIndex, fromLat, toLat, fromLon, toLon);
    }

    @GetMapping("/sshg")
    public JSONArray querySeaSurfaceHeight(@RequestParam(value = "time") String time,
                                           @RequestParam(value = "from_lat", defaultValue = "-74.5") float fromLat,
                                           @RequestParam(value = "to_lat", defaultValue = "64.5") float toLat,
                                           @RequestParam(value = "from_lon", defaultValue = "0.5") float fromLon,
                                           @RequestParam(value = "to_lon", defaultValue = "259.5") float toLon) {
        DateTime dateTime = DateTime.parse(time, DateTimeFormat.forPattern("yyyy-MM"));
        int timeIndex = (dateTime.getYear() - GodasConfig.START_TIME.getYear()) * 12 + dateTime.getMonthOfYear() - GodasConfig.START_TIME.getMonthOfYear();
        return new SeaSurfaceHeight().getSshg(timeIndex, fromLat, toLat, fromLon, toLon);
    }

    @GetMapping("/dbss_obil")
    public JSONArray queryGeometricDepthBelowSeaSurfaceIsothermalLayer(@RequestParam(value = "time") String time,
                                                                       @RequestParam(value = "from_lat", defaultValue = "-74.5") float fromLat,
                                                                       @RequestParam(value = "to_lat", defaultValue = "64.5") float toLat,
                                                                       @RequestParam(value = "from_lon", defaultValue = "0.5") float fromLon,
                                                                       @RequestParam(value = "to_lon", defaultValue = "259.5") float toLon) {
        DateTime dateTime = DateTime.parse(time, DateTimeFormat.forPattern("yyyy-MM"));
        int timeIndex = (dateTime.getYear() - GodasConfig.START_TIME.getYear()) * 12 + dateTime.getMonthOfYear() - GodasConfig.START_TIME.getMonthOfYear();
        return new GeometricDepthBelowSeaSurfaceIsothermalLayer().getDbss_obil(timeIndex, fromLat, toLat, fromLon, toLon);
    }

    @GetMapping("/dbss_obml")
    public JSONArray queryGeometricDepthBelowSeaSurfaceMixingLayer(@RequestParam(value = "time") String time,
                                                                   @RequestParam(value = "from_lat", defaultValue = "-74.5") float fromLat,
                                                                   @RequestParam(value = "to_lat", defaultValue = "64.5") float toLat,
                                                                   @RequestParam(value = "from_lon", defaultValue = "0.5") float fromLon,
                                                                   @RequestParam(value = "to_lon", defaultValue = "259.5") float toLon) {
        DateTime dateTime = DateTime.parse(time, DateTimeFormat.forPattern("yyyy-MM"));
        int timeIndex = (dateTime.getYear() - GodasConfig.START_TIME.getYear()) * 12 + dateTime.getMonthOfYear() - GodasConfig.START_TIME.getMonthOfYear();
        return new GeometricDepthBelowSeaSurfaceMixingLayer().getDbss_obml(timeIndex, fromLat, toLat, fromLon, toLon);
    }

    @GetMapping("/salt")
    public JSONArray querySalinity(@RequestParam(value = "time") String time,
                                   @RequestParam(value = "level") int level,
                                   @RequestParam(value = "from_lat", defaultValue = "-74.5") float fromLat,
                                   @RequestParam(value = "to_lat", defaultValue = "64.5") float toLat,
                                   @RequestParam(value = "from_lon", defaultValue = "0.5") float fromLon,
                                   @RequestParam(value = "to_lon", defaultValue = "259.5") float toLon) {
        DateTime dateTime = DateTime.parse(time, DateTimeFormat.forPattern("yyyy-MM"));
        int timeIndex = (dateTime.getYear() - GodasConfig.START_TIME.getYear()) * 12 + dateTime.getMonthOfYear() - GodasConfig.START_TIME.getMonthOfYear();
        return new Salinity().getSalt(timeIndex, level - 1, fromLat, toLat, fromLon, toLon);
    }

    @GetMapping("/cur")
    public JSONArray queryCurrent(@RequestParam(value = "time") String time,
                                  @RequestParam(value = "level") int level,
                                  @RequestParam(value = "from_lat", defaultValue = "-74.5") float fromLat,
                                  @RequestParam(value = "to_lat", defaultValue = "64.5") float toLat,
                                  @RequestParam(value = "from_lon", defaultValue = "0.5") float fromLon,
                                  @RequestParam(value = "to_lon", defaultValue = "259.5") float toLon) {
        DateTime dateTime = DateTime.parse(time, DateTimeFormat.forPattern("yyyy-MM"));
        int timeIndex = (dateTime.getYear() - GodasConfig.START_TIME.getYear()) * 12 + dateTime.getMonthOfYear() - GodasConfig.START_TIME.getMonthOfYear();
        return new Current().getCur(timeIndex, level - 1, fromLat, toLat, fromLon, toLon);
    }

    @GetMapping("/dzdt")
    public JSONArray queryGeometricVerticalVelocity(@RequestParam(value = "time") String time,
                                                    @RequestParam(value = "level") int level,
                                                    @RequestParam(value = "from_lat", defaultValue = "-74.5") float fromLat,
                                                    @RequestParam(value = "to_lat", defaultValue = "64.5") float toLat,
                                                    @RequestParam(value = "from_lon", defaultValue = "0.5") float fromLon,
                                                    @RequestParam(value = "to_lon", defaultValue = "259.5") float toLon) {
        DateTime dateTime = DateTime.parse(time, DateTimeFormat.forPattern("yyyy-MM"));
        int timeIndex = (dateTime.getYear() - GodasConfig.START_TIME.getYear()) * 12 + dateTime.getMonthOfYear() - GodasConfig.START_TIME.getMonthOfYear();
        return new GeometricVerticalVelocity().getDzdt(timeIndex, level - 1, fromLat, toLat, fromLon, toLon);
    }

    @GetMapping("/pottmp")
    public JSONArray queryPotentialTemperature(@RequestParam(value = "time") String time,
                                               @RequestParam(value = "level") int level,
                                               @RequestParam(value = "from_lat", defaultValue = "-74.5") float fromLat,
                                               @RequestParam(value = "to_lat", defaultValue = "64.5") float toLat,
                                               @RequestParam(value = "from_lon", defaultValue = "0.5") float fromLon,
                                               @RequestParam(value = "to_lon", defaultValue = "259.5") float toLon) {
        DateTime dateTime = DateTime.parse(time, DateTimeFormat.forPattern("yyyy-MM"));
        int timeIndex = (dateTime.getYear() - GodasConfig.START_TIME.getYear()) * 12 + dateTime.getMonthOfYear() - GodasConfig.START_TIME.getMonthOfYear();
        return new PotentialTemperature().getPottmp(timeIndex, level - 1, fromLat, toLat, fromLon, toLon);
    }
}
