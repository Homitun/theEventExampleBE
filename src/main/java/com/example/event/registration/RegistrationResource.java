package com.example.event.registration;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.io.UnsupportedEncodingException;
import com.example.event.event.Event;
import com.example.event.event.EventStatus;

@RestController
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:4401"})
@RequestMapping("api/v1/registration")
public class RegistrationResource {
    @Autowired
    private RegistrationService registrationService;   

    @PostMapping
    public ResponseEntity<String> registerEvent(@RequestBody Registration registration) throws UnsupportedEncodingException {
        return ResponseEntity.status(HttpStatus.CREATED).body(registrationService.registerUserForEvent(registration));
    }
    @GetMapping("/check-exist")
    public ResponseEntity<Boolean> isCheckEventIdAndUserId(@RequestParam(required = true) Integer userId,@RequestParam(required = true) Integer eventId) {
        
        return ResponseEntity.status(HttpStatus.OK).body(registrationService.isCheckEventIdAndUserId(userId, eventId));
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Event>> getEventByUserId(@PathVariable Integer userId,
    @RequestParam(required = false) EventStatus eventStatus,
        @RequestParam(required = false) Integer organizerId,
        @RequestParam(required = false) String eventName) {
        return ResponseEntity.status(HttpStatus.OK).body(registrationService.getEventByUserId(userId,eventStatus,organizerId,eventName));
    }
    @GetMapping("/user/{userId}/event/{eventId}")
    public ResponseEntity<Registration> getRegistrationByUserIdAndEventId(@PathVariable("userId") Integer userId,@PathVariable("eventId") Integer eventId) {
        return ResponseEntity.status(HttpStatus.OK).body(registrationService.getRegistrationByUserIdAndEventId(userId,eventId));
    }
    //Hàm lấy đăng ký của khách hàng show cho admin xem , sắp xếp theo ngày đăng ký và đồng thời có thể lọc theo sự kiện hoặc tên khách hàng cụ thể
    @GetMapping("order-by-registration-date/filter")
    public ResponseEntity<List<Registration>> getAllRegistrationByFilter(
        @RequestParam(required = false) Integer eventId,
        @RequestParam(required = false) String userFullname,
        @RequestParam(required = false) Integer status,
        @RequestParam(required = false) String monthYear,
        @RequestParam(required = false) String dayMonthYear
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(registrationService.getAllRegistrationByFilter(eventId,userFullname, status, monthYear, dayMonthYear));
    }
    @PatchMapping("/{id}/user/{userId}")
    public ResponseEntity<Void> updateById(@PathVariable Integer id,@PathVariable Integer userId,@RequestBody Registration registration) {
        registrationService.updateById(id,userId,registration);
        return ResponseEntity.noContent().build();
    }
    @PatchMapping("/{id}/user2/{userId}")
    public ResponseEntity<Void> updateById2(@PathVariable Integer id,@PathVariable Integer userId,@RequestBody Registration registration) {
        registrationService.updateById2(id,userId,registration);
        return ResponseEntity.noContent().build();
    }
}
