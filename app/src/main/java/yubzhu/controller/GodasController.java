package yubzhu.controller;

import org.json.JSONArray;
import org.springframework.web.bind.annotation.*;
import yubzhu.service.MomentumFlux;

/**
 * Created by 朱宇斌 on 2018/4/21
 */

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/godas")
public class GodasController {

    @GetMapping("/flx")
    public JSONArray queryMomentumFlux(@RequestParam(value = "time", defaultValue = "null") String time) {
        return new MomentumFlux().getFlx();
    }
}
