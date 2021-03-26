<?php

	require_once '../connection/conn.php';

	$viewrating	=	"SELECT * FROM rating";
	$viewrating	=	$conn -> query($viewrating);

	$response_data = null;
	while ($data = $viewrating -> fetch_assoc()) {
		$response_data[] = $data;
	}

	if (is_null($response_data)) {
		$status = false;
	} else {
		$status = true;
	}

	header('Content-Type: application/json');
	$response = ['status' => $status, "rating" => $response_data];
	echo json_encode($response);
?>