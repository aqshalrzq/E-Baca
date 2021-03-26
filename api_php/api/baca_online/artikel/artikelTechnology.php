<?php

	require_once '../connection/conn.php';

	$artikelTechnology	=	"SELECT * FROM artikel WHERE kategori = 'Technology' ORDER BY id_artikel DESC";
	$artikelTechnology	=	$conn -> query($artikelTechnology);

	$response_data_tech = null;
	while ($data = $artikelTechnology -> fetch_assoc()) {
		$response_data_tech[] = $data;
	}

	if (is_null($response_data_tech)) {
		$status = false;
	} else {
		$status = true;
	}

	header('Content-Type: application/json');
	$response = ['status' => $status, "artikel_technology" => $response_data_tech];
	echo json_encode($response);
?>