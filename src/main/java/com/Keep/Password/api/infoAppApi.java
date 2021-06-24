package com.Keep.Password.api;

import com.Keep.Password.Model.ReceivedInfoApp;
import com.Keep.Password.Model.SavedInfoApp;
import com.Keep.Password.Service.InfoAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("api/infoApp")
@RestController
public class infoAppApi {
    private final InfoAppService infoAppService;

    @Autowired
    public infoAppApi(InfoAppService infoAppService) {
        this.infoAppService = infoAppService;
    }

    @PostMapping
    public int addInfo(@RequestBody ReceivedInfoApp receivedInfoApp){
        return infoAppService.addInfo(receivedInfoApp.getApplicationName(),
                receivedInfoApp.getUserName(),
                receivedInfoApp.getPassword(),
                receivedInfoApp.getConfirm());

    }

    @GetMapping
    public List<SavedInfoApp> getAllInfo(){

        return infoAppService.getAllInfo();
    }

    @DeleteMapping(path = "/{id}")
    public int deleteInfo(@PathVariable("id") UUID id){

        return infoAppService.removeInfo(id);
    }
}
