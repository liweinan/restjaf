<script type="text/JavaScript">

var xmlhttp;

function test()
{
	execute('GET', 'http://localhost:8080/restandjaf/representations/testfile.txt');
	execute('POST', 'http://localhost:8080/restandjaf/representations/testfile.txt');
	execute('PUT', 'http://localhost:8080/restandjaf/representations/testfile.txt?thetexttoput');
	//execute('DELETE', 'http://localhost:8080/restandjaf/representations/testfile.txt');
}

function execute($method, $url, $data)
{
	xmlhttp = new XMLHttpRequest();
  xmlhttp.open($method, $url, true);
  xmlhttp.send($data);
}

</script>

<h3>Welcome to REST and JAF</h3>

<button onclick="test();">Test</button>
<br/>
--------------------------------------------
<br/>
<a href="javascript:execute('PUT', 'http://localhost:8080/restandjaf/representations/testfile.txt', 'Here is some PUT data');">PUT Resource</a>
<br/>
--------------------------------------------
<br/>
<a href="http://localhost:8080/restandjaf/representations/testfile.txt">GET Resource</a>
<br/>
--------------------------------------------

<form name="postdataform" action="/restandjaf/representations/testfile.txt" method="post">
  POST Resource Data: <input type="text" name="ResourceData"> <input type="submit" value="Update">
</form>

--------------------------------------------
<br/>
<a href="javascript:execute('DELETE', 'http://localhost:8080/restandjaf/representations/testfile.txt', null);">DELETE Resource</a>
