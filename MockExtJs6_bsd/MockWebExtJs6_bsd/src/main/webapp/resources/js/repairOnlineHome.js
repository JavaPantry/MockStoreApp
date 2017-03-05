$(document).ready(function(){
	//typeahead init
	// Defining the local dataset
	var productData = [
		{"id":1,"name":"EF 100-400mm f/4.5-5.6L IS USM"},
		{"id":2,"name":"EF 200-500mm f/4.5-5.6L IS USM"},
		{"id":3,"name":"EF 300-500mm f/4.5-5.6L IS USM"},
		{"id":4,"name":"EF 150-1500mm f/4.5-5.6L IS USM"},
		{"id":5,"name":"EF 230-500mm f/4.5-5.6L IS USM"},
		{"id":6,"name":"EF 220-500mm f/4.5-5.6L IS USM"},
		{"id":7,"name":"EF 350-500mm f/4.5-5.6L IS USM"},
		{"id":8,"name":"EF 430-500mm f/4.5-5.6L IS USM"},
		{"id":9,"name":"EF 670-500mm f/4.5-5.6L IS USM"},
		{"id":10,"name":"EF 600-500mm f/4.5-5.6L IS USM"}
	];

	// Constructing the suggestion engine
	var bloodhoundDS = new Bloodhound({
		datumTokenizer: Bloodhound.tokenizers.obj.whitespace(['id','name']),
		queryTokenizer: Bloodhound.tokenizers.whitespace,
		local: productData
	});
	bloodhoundDS.initialize();
	// Initializing the typeahead
	$('#scrollable-dropdown-menu .typeahead').typeahead({
			hint: true,
			highlight: true, /* Enable substring highlighting */
			minLength: 1 /* Specify minimum characters required for showing result */
		},
		{
			name: 'searchproduct',
			source: bloodhoundDS.ttAdapter(),
			displayKey: 'name',
			valueKey:"id",
			limit: 20
		});

	$('.typeahead').bind('typeahead:select', function(ev, suggestion) {
		//console.log('Selection: ' + suggestion);
		for(var prop in suggestion){
			console.log(prop + ': ' + suggestion[prop]);
		}
		$('#mySuppsId').val(suggestion['id']);
	});
	//end of typeahead init
});

/* bootstrap date picker require toggle*/
/*$('.input-group.date').datepicker({
	todayBtn: true,
	clearBtn: true,
	toggleActive: true
});*/

function postalValid(){
	var pCode = document.getElementById('postCode').value;
	var pCodeValid = document.getElementById('postCode').checkValidity();
	alert('pCodeValid = '+pCodeValid);
	return pCodeValid;
}
