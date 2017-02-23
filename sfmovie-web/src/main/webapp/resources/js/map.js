/**
 * 
 */
   function initialize() {
         // Center the map to be at San Francisco, the home city of Uber.
      var myLatlng = new google.maps.LatLng(37.7583, -122.4275);
      var mapOptions = {
          center: myLatlng,
      };
      // Select the map id canvas.
      var map = new google.maps.Map(document.getElementById("map-canvas"),
          mapOptions);
      // Create boundaries for the map.
      var defaultBounds = new google.maps.LatLngBounds(
          new google.maps.LatLng(37.73, -122.45),
          new google.maps.LatLng(37.82, -122.40));
      map.fitBounds(defaultBounds);
      // On page load, place ALL markers on Google Map. Each marker has a pop-up info
      // window containing info about the movie title, filming location, fun facts, and actors.
      // Create a marker for each place.
      markerArray = [];
      $.getJSON("/sfmovie/movie/getAll", function (data) {
    	  jQuery.each(data, function() {
    		  var coordPosition = new google.maps.LatLng(this.lat, this.lng);
              var marker = new google.maps.Marker({
                  map: map,
                  title: this.title,
                  position: coordPosition,
                  releaseYear: this.releaseYear,
                  director: this.director
              });
              markerArray.push(marker);
              var movieTitleText = this.title;
              var movieRTLink = encodeTextForRTLink(movieTitleText);
              var locationText = this.locations;
              var directorText = this.director;
              var directorRTLink = encodeTextForRTLink(directorText);
              var releaseYearText = this.releaseYear;
              var content = "<div class='content'><h4 class='movieTitle'><a class='window' target='_blank' href='http://www.rottentomatoes.com/m/"+movieRTLink +"''>"+ movieTitleText +"</a> " +" ("+ releaseYearText.toString() +")" +"</h4><div class='bodyContent'><p class='location'><p class='fun_facts'>" + "<b>Directed by</b><br><a class='window' target='_blank' href='http://www.rottentomatoes.com/celebrity/"+directorRTLink +"''>" + directorText  + "</a></p></div><b>Location</b><br>" + locationText + "</p>";
			
              var infowindow = new google.maps.InfoWindow({});
              google.maps.event.addListener(marker,'click', (function(marker,content,infowindow){
                  return function() {
                        infowindow.setContent(content);
                        infowindow.open(map,marker);
                        // DESIGN DECISION: Close the infowindow after 10 seconds to avoid crowding map with pop-ups.
                        setTimeout(function () { infowindow.close(); }, 10000);
                  };
              })(marker,content,infowindow));
    	  });
     });

      var releaseYearSlide = $("#slide");
      // Attach "change" event handler on the year slider and when slider changes, then markers are hidden or shown.
      releaseYearSlide.on("change", function() {
          document.getElementById("display_year").innerHTML = this.value;
          var input = $("#pac-input").val();
          input = input.toLowerCase().trim();
          for (var markerCount=0;markerCount<markerArray.length;markerCount++) {
        	  var movie = markerArray[markerCount];
              if (parseInt(movie.releaseYear) >= parseInt(this.value)) {
                  // Hide the marker locations that are less than the release year.
            	  if(input!="" && movie.title !== null && movie.title.toLowerCase() == input)  {
            		  markerArray[markerCount].setVisible(true);
            	  } else if (input=="") {
            		  markerArray[markerCount].setVisible(true);
            	  } else {
            		  markerArray[markerCount].setVisible(false);
            	  }
              } else {
                  markerArray[markerCount].setVisible(false);
              }
          }
      });
       // Use google API to position search box, year slider element, and year text element on top left inside map.
        $("#pac-input").autocomplete({
	        delay : 300,
	        minLength: 2,
	        appendTo : "#search-bar",
	        source : function (request, response) {
                var term = request.term;
                // Get relevant movies
                var movies = $.grep(markerArray, function (movie) {
                    return movie.title.toLowerCase()
                        .indexOf(term.toLowerCase()) > -1;
                });

                // Get their titles
                var titles = $.map(movies, function(movie) {
                    return movie.title;
                });

                // Remove duplicates
                titles = titles.filter(function(elem, pos) {
                    return titles.indexOf(elem) == pos;
                });

                // Return a reasonable number of suggestions
                if (titles.length > 10) {
                    titles = titles.slice(0,10);
                }

                response(titles);
	        },
	        select: function(event, ui) {
	        	selectMarkerToVisible(ui.item.value);
			}
        }); 
       var input = (document.getElementById('pac-input'));
       var yearSlider = (document.getElementById('release_year_filter'));
       var undoFilters = (document.getElementById('undo_all_filters'));
       map.controls[google.maps.ControlPosition.TOP_LEFT].push(input);
       map.controls[google.maps.ControlPosition.TOP_LEFT].push(yearSlider);
       map.controls[google.maps.ControlPosition.TOP_LEFT].push(undoFilters);
       
       $("#pac-input").keypress(function (event) {
           var ENTER_KEY = 13;
           if (event.keyCode === ENTER_KEY) {
        	   var input = $("#pac-input").val();
        	   selectMarkerToVisible(input);
           }
       });
        var undoAllFilters = $("#undo_all_filters");
        undoAllFilters.on("click", function() {
           setAllMap();
           $("#slide").val("1940");
           $("#pac-input").val("");
           document.getElementById("display_year").innerHTML ="1940";
        })
     }

  //----------------------------------------
  // Utility Functions.
  //----------------------------------------

  // Sets the map on all markers in the array.
  function setAllMap(map) {
      for (var markerCount = 0;markerCount < markerArray.length;markerCount++) {
          markerArray[markerCount].setVisible(true);
      }
  }
  
  function selectMarkerToVisible(input) {
	   input = input.toLowerCase();
	   for (var markerCount=0;markerCount<markerArray.length;markerCount++) {
   	   var movie=markerArray[markerCount];
   	   var minYear = document.getElementById("display_year").innerHTML;
   	   if(movie.title !== null && movie.title.toLowerCase() == input && parseInt(movie.releaseYear) >= parseInt(minYear)) {
   		   movie.setVisible(true);
   	   }
          else {
             movie.setVisible(false);
         }
       }
  }

  function encodeTextForRTLink(name) {
	  if (name == null) {
		 return name;
	  }
	  name = name.replace(/ /g,"_");
      return name;
  }
  google.maps.event.addDomListener(window, 'load', initialize);