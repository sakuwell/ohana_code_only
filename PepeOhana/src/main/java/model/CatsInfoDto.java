package model;

import java.sql.Date;
import java.sql.Timestamp;

/**----------------------------------------------------------------------*
 *Filename:CatsInfoDto.java
 *
 *Description:
 *	このクラスは、データベースの「cats_info」テーブルに対応した
 *	データ受け渡し用のクラスです。
 *	
 *	
 *Author:加藤、大久保
 *Creation Date:2023-09-04
 *
 *Copyright © 2023 KEG Sakura All rights reserved.
 *----------------------------------------------------------------------**/
public class CatsInfoDto {
	//----------------------------------------------------------------
	//フィールド
	//----------------------------------------------------------------
	private int		catId;         //ネコID
	private int		ownerId;       //飼い主のID
	private String	catName;       //ネコの名前
	private int	kind;		//ネコの種類
	private Date	birth;		//ネコの誕生日	
	private String 	age;       //ネコの年齢
	private int		gender;		//ネコの性別
	private Float		weight;		//ネコの体重
	private byte[]	image;	//ネコの写真
	private String 	comment;	//飼い主のコメント
	private Timestamp 	up_Date;	//更新日
	private Timestamp 	reg_Date;	//投稿日
	private String	userName;	//飼い主の名前
	private int		Id;	//飼い主の登録番号
	private int del;//削除フラグ


	//----------------------------------------------------------------
	//getter/setter
	//----------------------------------------------------------------

	//getter/setter（対象フィールド：catId）
	public int getCatId() { return catId; }
	public void setCatId(int catId) { this.catId = catId; }

	//getter/setter（対象フィールド：userId）
	public int getOwnerId() { return ownerId; }
	public void setOwnerId(int ownerId) { this.ownerId = ownerId; }

	//getter/setter（対象フィールド：catName）
	public String getCatName() { return catName; }
	public void setCatName(String catName) { this.catName = catName; }


	//getter/setter（対象フィールド：kind）
	public int getKind() { return kind; }
	public void setKind(int kind) { this.kind = kind; }

	//getter/setter (対象フィールド:birth)
	public Date getBirth() { return birth; }
	public void setBirth(Date birth) { this.birth = birth; }

	//getter/setter（対象フィールド：age）
	public String getAge() { return age; }
	public void setAge(String age) { this.age = age; }

	//getter/setter（対象フィールド：gender）
	public int getGender() { return gender; }
	public void setGender(int gender) { this.gender = gender; }

	//getter/setter（対象フィールド：weight）
	public Float getWeight() { return weight; }
	public void setWeight(Float weight) { this.weight = weight; }

	//getter/setter（対象フィールド：image）
	public byte[] getImage() { return image; }
	public void setImage(byte[] image) { this.image = image; }

	//getter/setter（対象フィールド：comment）
	public String getComment() { return comment; }
	public void setComment(String comment) { this.comment = comment; }

	//getter/setter（対象フィールド：Up_Dte：更新日）			
	public Timestamp getUp_Date() { return up_Date; }
	public void setUp_Date(Timestamp up_date) { this.up_Date = up_date; }

	//getter/setter（対象フィールド：Reg_Dte：登録日）			
	public Timestamp getReg_Date() { return reg_Date; }
	public void setReg_Date(Timestamp reg_date) { this.reg_Date = reg_date; }

	//getter/setter（対象フィールド：userName）
	public String getUserName() { return userName; }
	public void setUserName(String userName) { this.userName = userName; }

	//getter/setter（対象フィールド：userId）
	public int getId() { return Id; }
	public void setId(int Id) { this.Id = Id; }

	//getter/setter（対象フィールド：Del:削除フラグ）
	public int getDel() { return del; }
	public void setDel(int del) { this.del = del; }



}
