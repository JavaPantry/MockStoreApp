import { Component } from '@angular/core';
import { DomSanitizer, SafeHtml } from '@angular/platform-browser';

@Component({
    selector: 'auction-carousel',
    templateUrl: 'resources/app/components/carousel/carousel.html'
})
export default class CarouselComponent {

    imgHtml: SafeHtml;

    constructor(private sanitizer: DomSanitizer) {
      this.imgHtml = sanitizer.bypassSecurityTrustHtml(`
        <img class="slide-image" src="http://placehold.it/800x300" alt=""/>`);
    }
}