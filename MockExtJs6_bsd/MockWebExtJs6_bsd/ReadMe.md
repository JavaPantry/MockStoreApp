# Mock project
---

### TODOs 

1. TODO - <AP>: Switch from gson to jackson

	@Service class ApplicationConfigurationService already imports flexjson.JSONDeserializer

2. TODO - <AP>: TBR org/avp/quota/kpi/web/configuration/WebSecurityConfig:customAuthenticationProvider()
3. TODO - <AP>: later need to build separate org/avp/security jar project
4. TODO - <AP>: rename BsdController to ?_BsdSiteManagementController?_ 
5. TODO - <AP>: rename BsdClientController to ?_BsdClientUIController?_
6. TODO - <AP>: Really need to understand how to resolve conflicts in merging 
7. TODO - <AP>: Should I make extJs form submit json in bsd/update* ?
8. left one pointed to products within store (assigned to store)
9. right one pointed to products not within store (globally available but not assigned to selected store)
10. there are buttons between grids
11. [>] - move product from left to right (delete product from store)
12. [<] - move product from right to left (add product to store)
13. both grids have filter entry field in toolbar and beside it [clear filter] button enabled when filter is active

---

- Remove ExtJs Trial water mark

![water mark](Documents/ExtJsTrialWatermark.PNG)

- Store management page layout
 
![layout](Documents/AssignProductsToStore.PNG)


# References

1. Eclipse TypeScript Plug-in

An Eclipse [plug-in for developing in the TypeScript language](https://github.com/palantir/eclipse-typescript)
_Installation_

- Install Node.js
- Open Eclipse and go to Help->Install New Software
- Add the update site: http://eclipse-update.palantir.com/eclipse-typescript/
- Reboot Eclipse
- (optional) Right-click on a project and select Configure->Enable TypeScript Builder


2. What to choose in subclasses assign BUILD_DATABASE flag _OR_ use abstract method to override in subclasses
2.1. Google: `java can I assign const in subclass`

2.1.1. Best [http://stackoverflow.com/questions/8467494/overriding-constants-in-java](http://stackoverflow.com/questions/8467494/overriding-constants-in-java)
2.1.2. Force subclasses to include constant in abstract java class [stackoverflow](http://stackoverflow.com/questions/11896955/force-subclasses-to-include-constant-in-abstract-java-class)

2.1.3 Also good to read:

- [petrikainulainen abstract test classes](https://www.petrikainulainen.net/programming/testing/writing-clean-tests-it-starts-from-the-configuration/)
- [http://stackoverflow.com/questions/2211002/why-not-abstract-fields](http://stackoverflow.com/questions/2211002/why-not-abstract-fields)

3. [multiple instances of same grid](http://stackoverflow.com/questions/15777134/ext-js-multiple-instances-of-same-grid)

---

## Setup node_modules

- to update node package manager (npm) 

	- run `npm -v` to confirm current version  
	- in terminal window run `npm install npm -g`
	- run `npm -v` again confirm output 3.10.5 (means npm had been updated)

- in _src/main/webapp/resources/_ run npm install this will create `node_modules` folder with all required dependencies

---
