<?php

	require_once '../connection/conn.php';

	$artikelHealth	=	"SELECT * FROM artikel WHERE kategori = 'Health' ORDER BY id_artikel DESC";
	$artikelHealth	=	$conn -> query($artikelHealth);

	$response_data_heal = null;
	while ($data = $artikelHealth -> fetch_assoc()) {
		$response_data_heal[] = $data;
	}

	if (is_null($response_data_heal)) {
		$status = false;
	} else {
		$status = true;
	}

	header('Content-Type: application/json');
	$response = ['status' => $status, "artikel_health" => $response_data_heal];
	echo json_encode($response);
?>