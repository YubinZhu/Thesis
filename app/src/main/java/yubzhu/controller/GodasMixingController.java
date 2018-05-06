package yubzhu.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yubzhu.configure.GodasConfig;

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

    
}
