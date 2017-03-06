# Thymeleaf FAQ

###  How to set variables in a thymeleaf fragment?
- [3 recepies stackoverflow](http://stackoverflow.com/questions/20622823/how-to-set-variables-in-a-thymeleaf-fragment)
- [Passing multiple variables to a fragment](http://forum.thymeleaf.org/Passing-multiple-variables-to-a-fragment-td4025782.html)

### [spring mvc thymeleaf multiple template location](https://www.google.ca/search?q=thymeleaf+spring+multiple+template+location&oq=thymeleaf+spring+multiple+template+location&aqs=chrome..69i57.21295j0j7&sourceid=chrome&ie=UTF-8)
- [multiple template resolvers (thymeleaf) in spring mvc](http://stackoverflow.com/questions/33431614/multiple-template-resolvers-thymeleaf-in-spring-mvc)
- [Putting templates in subdirectories](http://forum.thymeleaf.org/Putting-templates-in-subdirectories-td4027257.html)

### [thymeleaf conditional display](https://www.google.ca/search?q=thymeleaf+spring+multiple+template+location&oq=thymeleaf+spring+multiple+template+location&aqs=chrome..69i57.21295j0j7&sourceid=chrome&ie=UTF-8#q=thymeleaf+conditional+display&*)
- [How to do if-else in Thymeleaf?](http://stackoverflow.com/questions/13494078/how-to-do-if-else-in-thymeleaf)

### [thymeleaf render model attribute prior it set in controller method](http://stackoverflow.com/questions/42627745/thymeleaf-render-model-attribute-prior-it-set-in-controller-method)

Also asked on [forum.thymeleaf.org](http://forum.thymeleaf.org/thymeleaf-render-model-attribute-prior-it-set-in-controller-method-td4030275.html)

> When I hit `/repairOnlineHome` url thymeleaf render wizardStep as not initialized despite it initialized in showRepairStep1().
If on next step I hit `/repairOnlineHome2` url thymeleaf render wizardStep with value set in previous showRepairStep1() i.e. wizardStep equal 1.
What's really surprize me that it looks like thymeleaf call getter for wizardStep before controller method set correct value.

    @Controller
    public class RepairController {
    
        private Integer wizardStep;// = new Integer(1);
    
        @ModelAttribute("wizardStep")
        public Integer wizardStep(){
            return wizardStep;
        }
        
    @RequestMapping({"/repairOnlineHome"})
    public String showRepairStep1(final SeedStarter seedStarter) {
        seedStarter.setDatePlanted(Calendar.getInstance().getTime());
        wizardStep = 1;
        return "repairOnlineHome";
    }

    @RequestMapping({"/repairOnlineHome2"})
    public String showRepairStep2(final SeedStarter seedStarter) {
        seedStarter.setDatePlanted(Calendar.getInstance().getTime());
        wizardStep = 2;
        return "repairOnlineHome2";
    }
    ...
    }

> In html I render wizardStep as 

    <div>WizardStep: <span th:text="${wizardStep}">wizardStepNo</span></div>

> My question is: Why thymeleaf render model attribute prior it set in controller method and how to fix this.


