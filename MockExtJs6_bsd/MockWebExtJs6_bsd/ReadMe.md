See file://..\ReadMe.md

## August 15


Fix mess with user accounts (user, bsduser, quota user), roles, user_roles, roles_priveleges, authorities etc.

For JPA inheritance types look at [JPA blog](http://www.thejavageek.com/jpa-tutorials/)
- [JPA table per concrete class example](http://www.thejavageek.com/2014/05/17/jpa-table-per-concrete-class-example/)
- [JPA Joined Inheritance Example](http://www.thejavageek.com/2014/05/17/jpa-joined-inheritance-example/)

- UserDao(quotaUser) BsdUser(users) extend org/avp/quota/kpi/model/security/User (hibernate inheritance)
- move BsdUser.email .etc to org/avp/quota/kpi/model/security/User (org/avp/quota/kpi/model/dao/UserDao) 
- make login against org/avp/quota/kpi/model/security/User instead of org/avp/quota/kpi/model/dao/UserDao 

Refactor AuthoritiesDao
- move AuthoritiesDao.java from _org/avp/quota/kpi/model/dao_ to _org/avp/quota/kpi/model/security/_
- rename AuthoritiesDao to Authority
- change AuthoritiesDao.user from type QuotaUser to org/avp/quota/kpi/model/security/User

### Angular mile stone v0.1.0.4
- AngularJs UI removed
- introduce ProductPriceInStore entity to link product and store
- updated test case to build database with Store and ProductPriceInStore

##  August 12

### Angular mile stone taken before removing AngularJs UI v0.1.0.3
- server side reading for products and orders
- three routes for product list, product detail and order list
- draft bootstrap orders table

## August 10

- to work on orders route
- create store <- prdInStore -> product


## August 09

Read products cause error in parsing standard json response

	map(response => response.json())

in home.ts change
 
      <div *ngFor="let product of products | async" class="col-sm-4 col-lg-4 col-md-4">
        <auction-product-item [product]="product"></auction-product-item>
      </div>
to

      <div *ngFor="let product of products.data | async" class="col-sm-4 col-lg-4 col-md-4">
        <auction-product-item [product]="product"></auction-product-item>
      </div>




### TODO
#### Eclipse TypeScript Plug-in

An Eclipse plug-in for developing in the TypeScript language.
https://github.com/palantir/eclipse-typescript
Installation

- Install Node.js
- Open Eclipse and go to Help->Install New Software
- Add the update site: http://eclipse-update.palantir.com/eclipse-typescript/
- Reboot Eclipse
- (optional) Right-click on a project and select Configure->Enable TypeScript Builder
