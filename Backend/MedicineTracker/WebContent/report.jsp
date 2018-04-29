<!DOCTYPE html>
<html lang="en">
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  
  <style>
  .car
  {
	margin: 5px auto;
	width: 750px;
	border-radius: 10px;
	border:2px solid #333;
	padding: 10px ;
	
  }
  .header
  {
	height:70px;
	background: linear-gradient(#8fb6f7, #cadbf7);
	border-radius: 10px;
	padding:10px
  }
  .leftside
  {
	float:left;
	width:50%
  }
  .top table,.top  tr,.top  td,.top  th
  {
  border:0!important;
  }
  .top td
  {width:25%;
  }
   </style>
</head>
<body>
<div class="container">
  <div class="car">

 <div class="header ">
 <div class="leftside">
 <h1>Bombay Hospital</h1>
 </div>
<div class="rightside">  
  <label>address: 12, Marine Lines, Mumbai - 400 020, India. </label>
</div>
</div>
 <table class="table top">
    <thead>
      <tr>
        <th>Name</th>
        <th>MR. Anthony john</th>
        <th>Lab No.</th>
		   <th>12334</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td>Age/Gender</td>
        <td>27yrs/Male</td>
		 <td>Registration Date </td>
        <td>11/01/2018</td>
      </tr>
      <tr>
        <td>Reffered by:</td>
        <td>j.k.verma</td>
		 <td>Reported Date:</td>
        <td>11/02/2018</td>
      </tr>
      
    </tbody> </table><br><br>
	 <table class="table bottom"><tbody>
	<tr>
        <th>Test Name</th>
        <th>Result</th>
        <th>Unit.</th>
		   <th>Range</th>
      </tr>
	  <tr>
        <th>RBC</th>
        <th>3.3</th>
        <th>10<sup>6</sup>/ul</th>
		   <th>3.5-5.5</th>
      </tr>
	 <tr>
        <th>Hemoglobin</th>
        <th>12</th>
        <th>g/dl</th>
		   <th>11-16</th>
      </tr>
	   <tr>
        <th>WBC</th>
        <th>6.7</th>
        <th>10<sup>3</sup>/ul</th>
		   <th>4.5-11</th>
      </tr>	
	   <tr>
        <th>PLT	</th>
        <th>256</th>
        <th>10<sup>3</sup>/ul</th>
		   <th>150-450</th>
      </tr>	
	   </tbody>
  </table>
</div>
</div>
</div>
</div>
</body>
</html>