package org.rak.starsBetween.floorplan

import org.springframework.web.bind.annotation.*

@CrossOrigin(origins = ["*"])
@RestController
@RequestMapping("/crew")
class CrewmanController {

    @PostMapping
    fun addCrewman(@RequestParam x: Int, @RequestParam y: Int) {
        println("Add Crewman")
    }

    @DeleteMapping
    fun removeCrewman(@RequestParam x: Int, @RequestParam y: Int) {
        println("Remove Crewman")
    }


    @PutMapping
    fun givOrder(@RequestParam crewmanId: Int, @RequestParam x: Int, @RequestParam y: Int){

    }



}
