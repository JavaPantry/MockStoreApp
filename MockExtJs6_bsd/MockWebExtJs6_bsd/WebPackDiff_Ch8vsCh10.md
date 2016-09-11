Main differences Ch8 vs. Ch10 WebPackDiff

- In c:\webStormWS\Angular2Workspace\auction\client\src\app\components\footer\footer.ts

	  templateUrl: 'app/components/footer/footer.html'
	to
	  template: require('./footer.html')

- In c:\webStormWS\Angular2Workspace\auction\client\src\app\components\home\home.ts
  
	   styleUrls: ['app/components/home/home.css'],
	 to
	   styles: [require('./home.css')],
 
 - In navbar.ts
 
	  templateUrl: 'app/components/navbar/navbar.html',
	to
	  template: require('./navbar.html'),

