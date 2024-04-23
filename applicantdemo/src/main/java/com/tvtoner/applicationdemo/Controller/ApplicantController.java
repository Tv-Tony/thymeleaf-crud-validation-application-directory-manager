package com.tvtoner.applicationdemo.Controller;

import com.tvtoner.applicationdemo.entity.Applicant;
import com.tvtoner.applicationdemo.service.ApplicantService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/applicants")
public class ApplicantController {

    private ApplicantService applicantService;

    @Autowired
    public ApplicantController(ApplicantService applicantService){
        this.applicantService = applicantService;
    }

    /**
     * InitBinder annotation is a form of preprocessing, the purpose of this method is to preprocess Strings that are
     * received from the form, we can also define an empty string here as null, and trim strings with white space,
     * we make a custom editor and include the parameter String.class to and include the stringTrimmerEditor, which
     * means that every string class we receive from a form will be preprocessed
     * @param dataBinder WebDataBinder instance to be initialized and customized
     */
    @InitBinder
    public void initBinder(WebDataBinder dataBinder){

        // Define the string trimmer (What we want to edit in the string)
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

        // define the class and the type of edit we want to do
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    /**
     * In this method we define a model, and add a list of applicants as an attribute to the model, the find all method
     * in the applicant Service will order the applicants by lastName, then its sent to the list-applicants html form
     * and from there with the given attribute we add the applicants into the table
     * @param theModel a model that we add attributes too allowing us to process them in the html file (Spring provides this instance)
     * @return returns the thymeleaf template on the browser
     */
    @GetMapping("/list")
    public String listApplicants(Model theModel){

        //Get employees from the DB
        List<Applicant> theApplicants = applicantService.findAll();

        //Add to the model
        theModel.addAttribute("applicants", theApplicants);

        return "list-applicants";
    }

    /**
     * This method simply adds a new Applicant to the model as an attribute to be processed in the HTML file, similar to
     * the update method but in this case we add a new applicant() that way when the setter methods are called since
     * no fields are defined the form will not be pre-populated with data, then when we enter submit, spring calls the
     * setter methods to set the data in the new Applicant that is given to us in the form
     * @param theModel a model that we add attributes too allowing us to process them in the html file (Spring provides this instance)
     * @return returns the thymeleaf template to add a new user in the browser
     */
    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel){

        // Define a new applicant
        Applicant theApplicant = new Applicant();

        // Add an applicant as an attribute
        theModel.addAttribute("applicant", theApplicant);

        return "applicant-form";
    }

    /**
     * This method processes if the data given us from the applicant-form meets our validity requirements, and with simple
     * logic decides if the applicant should be saved into the database or if we should return to the applicant-form
     * @param theApplicant applicant given to us from the Model, we define how it gets there in the HTML file
     * @param theBindingResult The parameter that holds the result of the data binding
     * @return If there is an error we are send back to the html applicant-form, and the messages from how we defined
     * the validity of the data will appear, else we save the applicant into the database and redirect to the main page
     */
    @PostMapping("/processForm")
    public String processForm(
            @Valid @ModelAttribute("applicant") Applicant theApplicant,
            BindingResult theBindingResult){

        System.out.println("\n");
        System.out.println(theBindingResult.toString());
        System.out.println("\n");

        if(theBindingResult.hasErrors()){
            return "applicant-form";
        }
        else {
            applicantService.save(theApplicant);

            System.out.println("\n");
            System.out.println(theApplicant.getFirstName() + " " + theApplicant.getLastName() + " Was Successfully Saved");
            System.out.println("\n");

            return "redirect:list";
        }
    }

//    @PostMapping("/save")
//    public String saveApplicant(@ModelAttribute("applicant") Applicant theApplicant){
//
//        applicantService.save(theApplicant);
//
//        return "redirect:list";
//    }

    /**
     * In the HTML method we send an HTTP POST request "applicantId" with the value of "tempApplicant.id", from here we
     * define an applicant and set it to the applicant with the given ID. Then it's added to an attribute as "applicant"
     * and from here spring automatically calls the getter methods to pre-populate the form, allowing us to see the data
     * before we update it.
     * @param theId the Applicants ID
     * @param theModel a model that we add attributes too allowing us to process them in the html file (Spring provides this instance)
     * @return returns the thymeleaf template to the browser
     */
    @PostMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("applicantId") int theId, Model theModel){

        // Define the applicant
        Applicant theApplicant = applicantService.findById(theId);

        //Add applicant to the model to then be processed in the html file
        theModel.addAttribute("applicant", theApplicant);

        return "applicant-form";
    }

    /**
     * This is a simple method that deletes the applicant from the database and redirects the user to the main page
     * @param theId id given us from the HTML POST request, allowing us to delete the given applicant
     * @return redirects to the main page list-applicants, after we finish deleting the applicant
     */
    @PostMapping("/delete")
    public String delete(@RequestParam("applicantId") int theId){

        Applicant tempApplicant = applicantService.findById(theId);

        // Delete applicant from the form
        applicantService.deleteById(theId);

        System.out.println("\n");
        System.out.println(tempApplicant.getFirstName() + " " + tempApplicant.getLastName() + " Was Successfully Deleted");
        System.out.println("\n");

        // Redirect to main page
        return "redirect:list";
    }

}
