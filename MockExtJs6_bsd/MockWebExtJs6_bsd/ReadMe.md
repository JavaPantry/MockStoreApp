See file://..\ReadMe.md

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
