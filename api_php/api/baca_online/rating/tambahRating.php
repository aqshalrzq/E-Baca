<?php

	require_once "../connection/conn.php";
	class create{}

	$nama		=	$_POST['nama'];
	$deskripsi	=	$_POST['deskripsi'];
	$rating		=	$_POST['rating'];

	if (empty($nama) || empty($deskripsi) || empty($rating)) {
		header('Content-Type: application/json');
		$response = new create();
		$response -> success = "0";
		$response -> message = "Anda tidak dapat mengirimkan form kosong.";
		echo json_encode($response);
	} else {
		$tambah = mysqli_query($conn, "INSERT INTO rating (nama, deskripsi, rating) VALUES ('$nama', '$deskripsi', '$rating')");
		if ($tambah) {
			header('Content-Type: application/json');
			$response = new create();
			$response -> success = "1";
			$response -> message = "Anda sukses menambahkan sebuah rating";
			echo json_encode($response);
		} else {
			header('Content-Type: application/json');
			$response = new create();
			$response -> success = "2";
			$response -> message = "Anda gagal menambahkan sebuah rating.";
			echo json_encode($response);
		}
	}

?>