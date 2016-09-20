import { Component } from '@angular/core';
import { DomSanitizer, SafeHtml } from '@angular/platform-browser';

@Component({
    selector: 'auction-carousel',
    templateUrl: 'resources/app/components/carousel/carousel.html'
})
export default class CarouselComponent {

    imgHtml1: SafeHtml;
    imgHtml2: SafeHtml;
    imgHtml3: SafeHtml;
    imgHtml4: SafeHtml;
    imgHtml5: SafeHtml;

    constructor(private sanitizer: DomSanitizer) {
      this.imgHtml1 = sanitizer.bypassSecurityTrustHtml(`
        <img class="slide-image" src="resources/images/slides/slide1.png" alt=""/>`);
      this.imgHtml2 = sanitizer.bypassSecurityTrustHtml(`
        <img class="slide-image" src="resources/images/slides/slide2.png" alt=""/>`);
      this.imgHtml3 = sanitizer.bypassSecurityTrustHtml(`
        <img class="slide-image" src="resources/images/slides/slide3.png" alt=""/>`);
      this.imgHtml4 = sanitizer.bypassSecurityTrustHtml(`
        <img class="slide-image" src="resources/images/slides/slide4.png" alt=""/>`);
      this.imgHtml5 = sanitizer.bypassSecurityTrustHtml(`
        <img class="slide-image" src="resources/images/slides/slide5.png" alt=""/>`);      
    }
}