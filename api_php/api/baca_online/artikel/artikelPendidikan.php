<?php

	require_once '../connection/conn.php';

	$artikelPendidikan	=	"SELECT * FROM artikel WHERE kategori = 'Pendidikan' ORDER BY id_artikel DESC";
	$artikelPendidikan	=	$conn -> query($artikelPendidikan);

	$response_data_pendidikan = null;
	while ($data = $artikelPendidikan -> fetch_assoc()) {
		$response_data_pendidikan[] = $data;
	}

	if (is_null($response_data_pendidikan)) {
		$status = false;
	} else {
		$status = true;
	}

	header('Content-Type: application/json');
	$response = ['status' => $status, "artikel_pendidikan" => $response_data_pendidikan];
	echo json_encode($response);
?>