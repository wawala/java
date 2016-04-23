package com.briup.ch13;

import java.util.Scanner;
/**
  ��ʦ��Ϣ����ϵͳ
  ÿ����ʦ��Ϣ���浽������
  ��ʦ���󱣴浽������
*/
public class Sms {
	private Teacher[] tchs;//���ڴ洢��ʦ����Ϣ
	private int index;	//���ڼ�¼�������ܹ��м�����ʦ

	//���캯�����ڳ�ʼ�������зǾ�̬����
	public Sms(){
		tchs = new Teacher[3];
		index = 0;
	}

	/**
	  ����
	  @param  ��ʦ����
	*/
	public void save(Teacher tchs){
		//����ĳ��Ȳ����Ա����ʦ�ˣ��������չ
		if(index >= tchs.length){
			Teacher[] demo = new Teacher[tchs.length + 3];
			//���鿽����tchs -> demo
			System.arraycopy(tchs,0,demo,0,index);
			tchs = demo;
		}
		tchs[index++] = tch;
	}

	/**
	  ��ѯ���еĽ�ʦ
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
		ͨ����ʦ��id���ҽ�ʦ����Ϣ
		 {{1001,terry,12},{1002,terry,12},null}
		 1002
	*/
	public Teacher queryById(long id){
		//��ȡ��id���������е�����
		int num = getIndexById(id);
		return num == -1?null:tchs[num];
	}

	/**
		ͨ��id��ȡ����Ϊ��id�Ľ�ʦ�������е�λ��
		 {{1001,terry,12},{1002,terry,12},null}
		id = 1002
		 1
	*/
	private int getIndexById(long id){
		int num = -1;//�ý�ʦ�Ҳ���
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
		�޸Ľ�ʦ��Ϣ
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
		ɾ����ʦ��Ϣ
	    id  name   age
	tchs = {
		{101 terry   12},
		{103 tom   12},
		{104 tom   12},
		null,
	}
		102
		�޸Ľ�ʦ��Ϣ
	*/
	public void deleteById(long id){
		int num = getIndexById(id);
		for(int i=num;i<index-1;i++){
			tchs[i] = tchs[i+1];
		}
		tchs[--index] = null;
	}
	
	public void menu(){
		System.out.println("********��ʦ����ϵͳ********");
		System.out.println("*1. ��ѯ���н�ʦ��Ϣ");
		System.out.println("*2. ¼���ʦ��Ϣ");
		System.out.println("*3. ɾ����ʦ��Ϣ");
		System.out.println("*4. ��ѯ������ʦ��Ϣ");
		System.out.println("*5. �޸Ľ�ʦ��Ϣ");
		System.out.println("*exit. �˳�");
		System.out.println("*help. ����");
		System.out.println("****************************");
	}
	/**
		������
	*/
	public static void main(String[] args){
		Sms sms = new Sms();
		sms.menu();
		Scanner sc = new Scanner(System.in);
		while(true){
			System.out.print("�����빦�ܱ�ţ�");
			String option = sc.nextLine();
			//System.out.println("�����ˣ�"+option);
			switch(option){
				case "1":
					System.out.println("�����ǽ�ʦ����Ϣ��");
					Teacher[] arr = sms.queryAll();
					for(int i=0;i<arr.length;i++){
						System.out.println(arr[i]);
					}
					System.out.println("�ܼ� "+sms.index+" ��");
					break;
				case "2":
					while(true){
						System.out.println("�������ʦ��Ϣ��id#name#age���������롾break��������һ��Ŀ¼");
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
						System.out.println("����ɹ���");
					}
					
					break;
				case "3":
					while(true){
						System.out.println("������Ҫɾ����ʦ�ĺ���������롾break��������һ��Ŀ¼");
						String idStr = sc.nextLine();
						if(idStr.equals("break")){
							break;//������ǰѭ�����������˵�
						}
						//1001#terry#12
						long id = Long.parseLong(idStr);
						Teacher oldTch = sms.queryById(id);
						if(oldTch == null){
							System.out.println("��Ҫɾ���Ľ�ʦ�����ڣ�");
							continue;
						}
						sms.deleteById(id);
						System.out.println("ɾ���ɹ���");
					}
					break;
				case "4":
					while(true){
						System.out.println("���������������롾break��������һ��Ŀ¼");
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
				case "5"://�޸�
					while(true){
						System.out.println("������Ҫ�޸Ľ�ʦ�ĺ���������롾break��������һ��Ŀ¼");
						String idStr = sc.nextLine();
						if(idStr.equals("break")){
							break;//������ǰѭ�����������˵�
						}
						//1001#terry#12
						long id = Long.parseLong(idStr);
						Teacher oldTch = sms.queryById(id);
						if(oldTch == null){
							System.out.println("��Ҫ�޸ĵĽ�ʦ�����ڣ�");
							continue;
						}
						System.out.println("ԭ����ϢΪ��"+oldTch);
						System.out.println("��������Ϣ��name#age��");
						//��ȡ�û�������Ϣ�����ҽ����װΪ����
						String newStr = sc.nextLine();
						String[] newArr = newStr.split("#");
						String name = newArr[0];
						int age = Integer.parseInt(newArr[1]);

						Teacher newTch = new Teacher(id,name,age);
						//�����޸�ģ��ķ���������޸Ĺ���
						sms.update(newTch);
						System.out.println("�޸ĳɹ�");
					}
					break;
				case "exit":
					System.out.println("bye bye,��ӭ�ٴ�ʹ�ã�");
					System.exit(0);
				case "help":
					sms.menu();
					break;
				default:
					System.out.println("���Ϸ����룡");

			}
		}
	}
}
