<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style> 
 
.scroll3
{

width:640px;
height:130px;
overflow:scroll;

}
 </style> 

<script>
 function changesOnchange(event){
	 
	 hideFields();
	 if(document.getElementById("relStatus").value=="select"){
		 alert("Select the proper Status");
		event.preventDefault();
	 }
	 if(document.getElementById("relStatus").value=="Single"||document.getElementById("relStatus").value=="Divorced"||
			 document.getElementById("relStatus").value=="Separated"||document.getElementById("relStatus").value=="Widowed"){
		 hideFields();
		 document.getElementById('warnLabel').style.visibility='visible';
		 
		 
	 }
		 
	 if(document.getElementById("relStatus").value=="In a Relationship"||document.getElementById("relStatus").value=="In an Open Relationship"){
		 
		 hideFields();
		 document.getElementById('withLabel').style.visibility='visible';
		 document.getElementById('warnLabel').style.visibility='hidden';
		 document.getElementById('relWith').style.visibility='visible';
		 document.getElementById('anniLabel').style.visibility='visible';
		 document.getElementById('days').style.visibility='visible';
		 document.getElementById('months').style.visibility='visible';
		 document.getElementById('years').style.visibility='visible';
	 }
	 if(document.getElementById("relStatus").value=="Engaged"){
		 hideFields();
		 document.getElementById('toLabel').style.visibility='visible';
		 document.getElementById('relWith').style.visibility='visible';
		 document.getElementById('sinceLabel').style.visibility='visible';
		 document.getElementById('days').style.visibility='visible';
		 document.getElementById('months').style.visibility='visible';
		 document.getElementById('years').style.visibility='visible';
	 }
	 if(document.getElementById("relStatus").value=="Married"){
		 hideFields();
		 document.getElementById('toLabel').style.visibility='visible';
		 document.getElementById('relWith').style.visibility='visible';
		 document.getElementById('anniLabel').style.visibility='visible';
		 document.getElementById('days').style.visibility='visible';
		 document.getElementById('months').style.visibility='visible';
		 document.getElementById('years').style.visibility='visible';
	 }
	 if(document.getElementById("relStatus").value=="Its Complicated"){
		 hideFields();
		 document.getElementById('withLabel').style.visibility='visible';
		 document.getElementById('relWith').style.visibility='visible';
		 document.getElementById('sinceLabel').style.visibility='visible';
		 document.getElementById('days').style.visibility='visible';
		 document.getElementById('months').style.visibility='visible';
		 document.getElementById('years').style.visibility='visible';
	 }
 }
 
 function hideFields(){
	 
	 document.getElementById('warnLabel').style.visibility='hidden';
	 document.getElementById('withLabel').style.visibility='hidden';
	 document.getElementById('relWith').style.visibility='hidden';
	 document.getElementById('toLabel').style.visibility='hidden';
	 document.getElementById('anniLabel').style.visibility='hidden';
	 document.getElementById('days').style.visibility='hidden';
	 document.getElementById('months').style.visibility='hidden';
	 document.getElementById('years').style.visibility='hidden';
	 document.getElementById('sinceLabel').style.visibility='hidden';
 }
 
 
 function goBack(){
	 document.getElementById("relForm").action="About";
		document.getElementById("relForm").submit();
 }
</script>
</head>
<body onload="hideFields();">
<center>
<div class="panel panel-default" style="height:auto">
<h1>Change Relationship Status</h1>
<form action="saveRelStatus" id="relForm">
  <s:select  label="" 
		 list="relStatusList" name="relStatusList" onchange="changesOnchange(event)" id="relStatus" style="color:black" >
		
  </s:select>
  <label for="withLabel" id="withLabel">with</label>
  <label for="toLabel" id="toLabel">to</label>
  <br/><br/>
  <label for="warnLabel" id="warnLabel">This will not be shown in news feed</label>
  <INPUT TYPE="text" NAME="relWith" id="relWith"/>
  <br/>
  <label for="anniLabel" id="anniLabel">Anniversary:</label>
  <label for="sinceLabel" id="sinceLabel">Since:</label>
  <select  name="months" id="months" >
  <option selected="selected">Month:</option>
 	<option>Jan</option>
 	<option>Feb</option>
 	<option>Mar</option>
 	<option>Apr</option>
 	<option>May</option>
 	<option>Jun</option>
 	<option>Jul</option>
 	<option>Aug</option>
 	<option>Sep</option>
 	<option>Oct</option>
 	<option>Nov</option>
 	<option>Dec</option>
 </select>
 <select  name="days" id="days" >
 <option selected="selected">Day:</option>
 	<option>1</option>
 	<option>2</option>
 	<option>3</option>
 	<option>4</option>
 	<option>5</option>
 	<option>6</option>
 	<option>7</option>
 	<option>8</option>
 	<option>9</option>
 	<option>10</option>
 	<option>11</option>
 	<option>12</option>
 	<option>13</option>
 	<option>14</option>
 	<option>15</option>
 	<option>16</option>
 	<option>17</option>
 	<option>18</option>
 	<option>19</option>
 	<option>20</option>
 	<option>21</option>
 	<option>22</option>
 	<option>23</option>
 	<option>24</option>
 	<option>25</option>
 	<option>26</option>
 	<option>27</option>
 	<option>28</option>
 	<option>29</option>
 	<option>30</option>
 	<option>31</option>
  </select>
 <select  name="years" id="years" >
 <option selected="selected">Year:</option>
 	<option>2000</option>
 	<option>2001</option>
 	<option>2002</option>
 	<option>2003</option>
 	<option>2004</option>
 	<option>2005</option>
 	<option>2006</option>
 	<option>2007</option>
 	<option>2008</option>
 	<option>2009</option>
 	<option>2010</option>
 	<option>2011</option>
 	<option>2012</option>
 	<option>2013</option>
 	<option>2014</option>
 	 </select>
 
  <br/><br/>
  <input type="submit" value="Save" id="save" onclick="changesOnchange(event);"/>&nbsp;&nbsp;
<input type="button" onclick="goBack()" value="Cancel" id="cancel"/>
</form>
  
  <script type="text/javascript">
hideFields();
</script>
</div>
</center>
</body>
</html>