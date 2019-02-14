function checkCardId(socialNo) {
	if (socialNo == "") {
		alert("�������֤���벻��Ϊ��!");
		return (false);
	}
	if (socialNo.length != 15 && socialNo.length != 18) {
		alert("�������֤�����ʽ����ȷ!");
		return (false);
	}
	var area = {
		11 : "����",
		12 : "���",
		13 : "�ӱ�",
		14 : "ɽ��",
		15 : "���ɹ�",
		21 : "����",
		22 : "����",
		23 : "������",
		31 : "�Ϻ�",
		32 : "����",
		33 : "�㽭",
		34 : "����",
		35 : "����",
		36 : "����",
		37 : "ɽ��",
		41 : "����",
		42 : "����",
		43 : "����",
		44 : "�㶫",
		45 : "����",
		46 : "����",
		50 : "����",
		51 : "�Ĵ�",
		52 : "����",
		53 : "����",
		54 : "����",
		61 : "����",
		62 : "����",
		63 : "�ຣ",
		64 : "����",
		65 : "�½�",
		71 : "̨��",
		81 : "���",
		82 : "����",
		91 : "����"
	};
	if (area[parseInt(socialNo.substr(0, 2))] == null) {
		alert("���֤���벻��ȷ(�����Ƿ�)!");
		return (false);
	}
	if (socialNo.length == 15) {
		pattern = /^\d{15}$/;
		if (pattern.exec(socialNo) == null) {
			alert("15λ���֤�������Ϊ���֣�");
			return (false);
		}
		var birth = parseInt("19" + socialNo.substr(6, 2));
		var month = socialNo.substr(8, 2);
		var day = parseInt(socialNo.substr(10, 2));
		switch (month) {
		case '01':
		case '03':
		case '05':
		case '07':
		case '08':
		case '10':
		case '12':
			if (day > 31) {
				alert('�������֤���벻��ʽ��ȷ!');
				return false;
			}
			break;
		case '04':
		case '06':
		case '09':
		case '11':
			if (day > 30) {
				alert('�������֤���벻��ʽ��ȷ!');
				return false;
			}
			break;
		case '02':
			if ((birth % 4 == 0 && birth % 100 != 0) || birth % 400 == 0) {
				if (day > 29) {
					alert('�������֤���벻��ʽ��ȷ!');
					return false;
				}
			} else {
				if (day > 28) {
					alert('�������֤���벻��ʽ��ȷ!');
					return false;
				}
			}
			break;
		default:
			alert('�������֤���벻��ʽ��ȷ!');
			return false;
		}
		var nowYear = new Date().getYear();
		if (nowYear - parseInt(birth) < 15 || nowYear - parseInt(birth) > 100) {
			alert('�������֤���벻��ʽ��ȷ!');
			return false;
		}
		return (true);
	}
	var Wi = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1);
	var lSum = 0;
	var nNum = 0;
	var nCheckSum = 0;
	for (i = 0; i < 17; ++i) {
		if (socialNo.charAt(i) < '0' || socialNo.charAt(i) > '9') {
			alert("�������֤�����ʽ����ȷ!");
			return (false);
		} else {
			nNum = socialNo.charAt(i) - '0';
		}
		lSum += nNum * Wi[i];
	}
	if (socialNo.charAt(17) == 'X' || socialNo.charAt(17) == 'x') {
		lSum += 10 * Wi[17];
	} else if (socialNo.charAt(17) < '0' || socialNo.charAt(17) > '9') {
		alert("�������֤�����ʽ����ȷ!");
		return (false);
	} else {
		lSum += (socialNo.charAt(17) - '0') * Wi[17];
	}
	if ((lSum % 11) == 1) {
		return true;
	} else {
		alert("�������֤�����ʽ����ȷ!");
		return (false);
	}
}