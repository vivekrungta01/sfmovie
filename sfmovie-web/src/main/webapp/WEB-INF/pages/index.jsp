<!DOCTYPE html>
<html>
  <head>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
    <link type="text/css" rel="stylesheet" href="/resources/css/map.css"/>
    <link type="text/css" rel="stylesheet" href="/resources/css/jquery-ui.css"/>
    <script type="text/javascript"
      src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAhinD9vSOZq5Bd7iRSloBUqASc1wcYMks&sensor=true&libraries=places">
    </script>
    <script type="text/javascript" src="/resources/js/jquery-1.8.2.min.js"></script>
    <script type="text/javascript" src="/resources/js/jquery-ui.js"></script>
    <script type="text/javascript" src="/resources/js/map.js"></script>
   <!--  <script type="text/javascript" src="/resources/js/jquery-ui-1.9.0.full.min.js"></script> -->
    
  </head>
  <body>
  <div id="search-bar">
    <input id="pac-input" class="controls" type="text" placeholder="Enter movie title">
    </div>
    <div id="release_year_filter">
      <div class="filter_instruction">Show only movies with this release year or higher.<br></div>
      <input id="slide" type="range"  min="1940" max="2020" value="1940"/>
      <span id="display_year">1940</span>
    </div>
    <button id="undo_all_filters">Undo All Filters</button>
    <h1 class="ribbon">
      <strong class="ribbon-content">
        <div class="app_title">SF Film Locations</div><br>
        <div class="author">Made by <a target='_blank' href="https://www.github.com/vivekrungta01/">Vivek Rungta</a><br>with Spring connected to MongoDB and Redis
        </div>
      </strong>
    </h1>
    <div id="map-canvas"></div>
  </body>
</html>