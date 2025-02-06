package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.cache.CacheService;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.service.UserEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserEntryService userEntryService;

    @Autowired
    CacheService cacheService;

    @GetMapping("/get-users")
    public ResponseEntity<?> getAllUsers() {
        List<User> all = userEntryService.getAll();
        if(all != null && !all.isEmpty()) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create-admin-user")
    public void createAdmin(@RequestBody User admin) {
        userEntryService.saveAdmin(admin);
    }

    @GetMapping("clear-app-cache")
    public void clearCache() {
        cacheService.init();
    }
}
