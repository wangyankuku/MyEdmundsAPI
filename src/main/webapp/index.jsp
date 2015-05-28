
<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="Cache-Control"
	content="no-cache, no-store, must-revalidate">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Expires" content="0">

</head>

<body>

	<div class="container">
		<div class="row">
			<div class="span12">
				<h3>Guide to consume the Edmunds Vehicle VIN API:</h3>
				<br>
				<dl>
					<dt>
						<b>URL:</b>
					</dt>
					<dd>http://localhost:8080/VehicleAPI/api/vin/{VIN}</dd>
					<dd>(Please replace the localhost with the real domain name in production environment)</dd>
					<br>
					<dt>
						<b>HTTP METHOD:</b>
					</dt>
					<dd>GET</dd>
					<br>
					<dt>
						<b>HEADERS:</b>
					</dt>

					<dd>HEADER: apiKey</dd>
					<dd>VALUE: {Your API Key String}</dd>
					<dd>
						(Please go to <a href="http://developer.edmunds.com/">
							Edmunds.com Developer Network</a> to register your own api key.)
					</dd>
					<br>
					<dt>
						<b>RETURN VALUE:</b>
					</dt>
					<dd>
						If the request is accepted, the server response is "200 OK". <br>
						API returns info in JSON format with the vehicle basic information
						including make, model, submodel, year, average customer rating,
						editor's summary rating, engine type and MPG.<br> <br>
						eg:<br>
						<code>
						{<br>
						   "MPG": {<br>
						      "cityMPG": 23,<br>
						      "highwayMPG": 31<br>
						   },<br>
						   "averageCustomerRating": 3.929,<br>
						   "engineType": "gas",<br>
						   "make": "Honda",<br>
						   "model": "CR-V",<br>
						   "subModel": "SUV",<br>
						   "summary": "The Honda CR-V has long been one of our favorite compact crossover SUVs,
											and an update for 2012 makes it more appealing than ever.
											Our only gripes include a wish for a 6-speed automatic and better road noise suppression.",<br>
						   "year": "2014"<br>
						}<br>
						</code>
						<br>
						If the api key is missing, the response status is "403 Forbidden". <br>
						If the key is not valid, the response status is "401 Unauthorized".<br> 
						If the vehicle with this vin is not found, the response status is "400 Bad Request".
					</dd>
				</dl>
			</div>
		</div>
	</div>

</body>

</html>

