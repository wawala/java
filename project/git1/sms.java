package com.briup.ch13;

import java.util.Scanner;
/**
  教师信息管理系统
  每个教师信息保存到对象中
  教师对象保存到数组中
*/
public class Sms {
	private Teacher[] tchs;//用于存储教师的信息
	private int index;	//用于记录数组中总共有几个教师

	//构造函数用于初始化对象中非静态属性
	public Sms(){
		tchs = new Teacher[3];
		index = 0;
	}

	/**
	  保存
	  @param  教师对象
	*/
	public void save(Teacher tchs){
		//数组的长度不足以保存教师了，数组的扩展
		if(index >= tchs.length){
			Teacher[] demo = new Teacher[tchs.length + 3];
			//数组拷贝，tchs -> demo
			System.arraycopy(tchs,0,demo,0,index);
			tchs = demo;
		}
		tchs[index++] = tch;
	}

	/**
	  查询所有的教师
	  tchs= new Teacher[3];
	  {{1001,terry,12},{1002,terry,12},null}
	  {{1001,terry,12},{1002,terry,12}}
	  index = 1
	*/
	public Teacher[] queryAll(){
		Teacher[] demo = new Teacher[index];
		System.arraycopy(tchs,0,demo,0,index);
		return demo;
	}

	/**
		通过教师的id查找教师的信息
		 {{1001,terry,12},{1002,terry,12},null}
		 1002
	*/
	public Teacher queryById(long id){
		//获取该id所在数组中的索引
		int num = getIndexById(id);
		return num == -1?null:tchs[num];
	}

	/**
		通过id获取号码为该id的教师在数组中的位置
		 {{1001,terry,12},{1002,terry,12},null}
		id = 1002
		 1
	*/
	private int getIndexById(long id){
		int num = -1;//该教师找不到
		for(int i=0;i<index;i++){
			if(tchs[i].getId() == id){
				num = i;
				break;
			}
		}
		return num;
	}
	/**
	    id  name   age
		101 terry   12
		102 jacky   12
		102 jacky  12
		修改教师信息
	*/
	public void update(Teacher newTch){
		for(int i=0;i<index;i++){
			if(newTch.getId() == tch[i].getId()){
				tchs[i].setName(newTch.getName());
				tchs[i].setAge(newTch.getAge());
			}
		}
	}
	/**
		删除教师信息
	    id  name   age
	tchs = {
		{101 terry   12},
		{103 tom   12},
		{104 tom   12},
		null,
	}
		102
		修改教师信息
	*/
	public void deleteById(long id){
		int num = getIndexById(id);
		for(int i=num;i<index-1;i++){
			tchs[i] = tchs[i+1];
		}
		tchs[--index] = null;
	}
	
	public void menu(){
		System.out.println("********教师管理系统********");
		System.out.println("*1. 查询所有教师信息");
		System.out.println("*2. 录入教师信息");
		System.out.println("*3. 删除教师信息");
		System.out.println("*4. 查询单个教师信息");
		System.out.println("*5. 修改教师信息");
		System.out.println("*exit. 退出");
		System.out.println("*help. 帮助");
		System.out.println("****************************");
	}
	/**
		主方法
	*/
	public static void main(String[] args){
		Sms sms = new Sms();
		sms.menu();
		Scanner sc = new Scanner(System.in);
		while(true){
			System.out.print("请输入功能编号：");
			String option = sc.nextLine();
			//System.out.println("接收了："+option);
			switch(option){
				case "1":
					System.out.println("以下是教师的信息：");
					Teacher[] arr = sms.queryAll();
					for(int i=0;i<arr.length;i++){
						System.out.println(arr[i]);
					}
					System.out.println("总计 "+sms.index+" 个");
					break;
				case "2":
					while(true){
						System.out.println("请输入教师信息【id#name#age】或者输入【break】返回上一级目录");
						String TchStr = sc.nextLine();
						if(Tchtr.equals("break")){
							break;
						}
						//1001#terry#12
						String[] tchArr = tchStr.split("#");
						long id = Long.parseLong(stuArr[0]);
						String name = tchArr[1];
						int age = Integer.parseInt(tchArr[2]);
						Teacher tch = new Teacher(id,name,age);
						sms.save(tch);
						System.out.println("保存成功！");
					}
					
					break;
				case "3":
					while(true){
						System.out.println("请输入要删除教师的号码或者输入【break】返回上一级目录");
						String idStr = sc.nextLine();
						if(idStr.equals("break")){
							break;//跳出当前循环，返回主菜单
						}
						//1001#terry#12
						long id = Long.parseLong(idStr);
						Teacher oldTch = sms.queryById(id);
						if(oldTch == null){
							System.out.println("您要删除的教师不存在！");
							continue;
						}
						sms.deleteById(id);
						System.out.println("删除成功！");
					}
					break;
				case "4":
					while(true){
						System.out.println("请输入号码或者输入【break】返回上一级目录");
						String idStr = sc.nextLine();
						if(idStr.equals("break")){
							break;
						}
						//1001#terry#12
						long id = Long.parseLong(idStr);
						Teacher tch= sms.queryById(id);
						System.out.println(tch==null?"sorry,not found!":tch);
					}
					break;
				case "5"://修改
					while(true){
						System.out.println("请输入要修改教师的号码或者输入【break】返回上一级目录");
						String idStr = sc.nextLine();
						if(idStr.equals("break")){
							break;//跳出当前循环，返回主菜单
						}
						//1001#terry#12
						long id = Long.parseLong(idStr);
						Teacher oldTch = sms.queryById(id);
						if(oldTch == null){
							System.out.println("您要修改的教师不存在！");
							continue;
						}
						System.out.println("原有信息为："+oldTch);
						System.out.println("请输入信息【name#age】");
						//获取用户的新信息，并且将其封装为对象
						String newStr = sc.nextLine();
						String[] newArr = newStr.split("#");
						String name = newArr[0];
						int age = Integer.parseInt(newArr[1]);

						Teacher newTch = new Teacher(id,name,age);
						//调用修改模块的方法，完成修改功能
						sms.update(newTch);
						System.out.println("修改成功");
					}
					break;
				case "exit":
					System.out.println("bye bye,欢迎再次使用！");
					System.exit(0);
				case "help":
					sms.menu();
					break;
				default:
					System.out.println("不合法输入！");

			}
		}
	}
}
