<?php

	require_once "../connection/conn.php";

	$viewteam	=	"SELECT * FROM team";
	$viewteam	=	$conn -> query($viewteam);

	$response_data = null;
	while ($data = $viewteam -> fetch_assoc()) {
		$response_data[] = $data;
	}

	if (is_null ($response_data)) {
		$status = false;
	} else {
		$status = true;
	}

	header('Content-Type: application/json');
	$response = ['status' => $status, "data_team" => $response_data];
	echo json_encode($response);

?>