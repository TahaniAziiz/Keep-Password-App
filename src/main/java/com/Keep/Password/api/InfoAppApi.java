package com.Keep.Password.api;

import com.Keep.Password.Model.InfoApp.ReceivedInfoApp;
import com.Keep.Password.Model.InfoApp.SavedInfoApp;
import com.Keep.Password.Model.InfoApp.UpdatePassword;
import com.Keep.Password.Service.InfoAppService;
import com.sun.istack.internal.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("api/infoApp")
@RestController
public class InfoAppApi {
    private final InfoAppService infoAppService;

    @Autowired
    public InfoAppApi(InfoAppService infoAppService) {
        this.infoAppService = infoAppService;
    }

    @PostMapping
    public int addInfo(@NotNull @Validated @RequestBody ReceivedInfoApp receivedInfoApp){
        return infoAppService.addInfo(receivedInfoApp.getApplicationName(),
                receivedInfoApp.getUserName(),
                receivedInfoApp.getPassword(),
                receivedInfoApp.getConfirm());
    }

    @GetMapping
    public List<SavedInfoApp> getAllInfo(){

        return infoAppService.getAllInfo();
    }

    @GetMapping(path = "/{id}")
    public SavedInfoApp getSpecificItem(@PathVariable("id") UUID id){
        return infoAppService.getSpecificItem(id);
    }

    @DeleteMapping(path = "/{id}")
    public int deleteInfo(@PathVariable("id") UUID id){

        return infoAppService.removeInfo(id);
    }

    @PutMapping(path = "/{id}/updateUserName")
    public int updateUserName(@PathVariable("id") UUID id, @NotNull @RequestBody String userName){
        return infoAppService.updateUserName(id, userName);

    }

    @PutMapping(path = "/{id}/updatePassword")
    public int updatePassword(@PathVariable("id") UUID id, @NotNull @Validated @RequestBody UpdatePassword updatePassword){
        return infoAppService.updatePassword(id, updatePassword);

    }

}
