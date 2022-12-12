package com.sampson.dataStructures.sparsearry;


import java.io.*;

public class SparseArray {
    public static void main(String[] args) {
        // 创建一个原始的二维数组 11 * 11
        // 0: 表示没有棋子， 1 表示 黑子 2 表蓝子
        int[][] chessArr1 = new int[11][11];
        //给该数组模拟赋值
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        chessArr1[4][5] = 2;
        System.out.println("原始的数组~~~~~");
        for (int[] row : chessArr1) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
        System.out.println();

        //定义一个sum，用来记录原始数组中有几个非零值，得到sum后就可以确定稀疏数组的行=sum+1，列为3
        int sum = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr1[i][j] != 0) {
                    sum++;
                }
            }
        }
        //定义新的稀疏数组
        int[][] sparseArr = new int[sum + 1][3];
        //给稀疏数组的第一行赋值，依次为原始数组的行数，原始数组的列数，原始数组的有效数据个数
        //TODO 该稀疏数组的第一行第三列可以考虑存储原始数组非有效数据的值
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;

        //count 用来记录是第几个有效数据
        int count = 0;
        //遍历原始数组，将有效数据存入稀疏数组中
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr1[i][j] != 0) {
                    count++;
                    //如果是第一个有效数据，该count=1，放入稀疏数组的第一行
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr1[i][j];
                }
            }
        }
        // 输出稀疏数组的形式
        System.out.println("得到稀疏数组为~~");
        int sparseArr_length = sparseArr.length;
        for (int i = 0; i < sparseArr_length; i++) {
            System.out.printf("%d\t%d\t%d\t\n", sparseArr[i][0], sparseArr[i][1], sparseArr[i][2]);
        }
        System.out.println();
        //将稀疏数组转换为原始数组
		/*
		 *  1. 先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组，比如上面的chessArr2 = int [11][11]
			2. 再读取稀疏数组后几行的数据，并赋给 原始的二维数组 即可.
		 */

        //1. 先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组
        int[][] chessArr2 = new int[sparseArr[0][0]][sparseArr[0][1]];
        //2. 再读取稀疏数组后几行的数据(从第二行开始)，并赋给原始的二维数组即可
        for (int i = 1; i < sparseArr.length; i++) {
            chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }
        // 输出还原后的二维数组
        System.out.println("还原后的二维数组为~~");
        for (int[] row : chessArr2) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
        //TODO 将稀疏数组存入磁盘
        System.out.println();
        System.out.println("将稀疏数组存入磁盘中");
        //这里将文件保存在项目中
        File f = new File("atguigu/file");
        //判断该目录是否存在，不存在就创建
        if (!f.exists()) {
            f.mkdir();
        }
        File data = new File("atguigu/file/map.text");
        FileOutputStream out = null;
        OutputStreamWriter osw = null;
        try {
            out = new FileOutputStream(data);
            osw = new OutputStreamWriter(out, "UTF-8");
            System.out.println("写入中-------------------");
            for (int i = 0; i < sparseArr_length; i++) {
                osw.write(sparseArr[i][0] + "," + sparseArr[i][1] + "," + sparseArr[i][2] + ",");
            }
            osw.close();
            out.close();
            System.out.println("写入磁盘成功--------------");
        } catch (Exception e) {
            e.printStackTrace();
        }
        //TODO 从磁盘中读取稀疏数组
        System.out.println();
        System.out.println("从磁盘中读取稀疏数组");
        File file = new File("atguigu/file/map.text");
        FileInputStream in = null;
        InputStreamReader inputStreamReader = null;
        try {
            in = new FileInputStream(file);
            inputStreamReader = new InputStreamReader(in, "UTF-8");
            StringBuilder builder = new StringBuilder();
            while (inputStreamReader.ready()) {
                builder.append((char) inputStreamReader.read());
            }
            inputStreamReader.close();
            in.close();
            System.out.println("读取成功");
            String ss = builder.toString();
            String[] split = ss.split(",");
            //恢复稀疏数组
            int sum1 = 0;
            int[][] sparseArr1 = new int[split.length / 3][3];
            sparseArr1[0][0] = Integer.parseInt(split[0]);
            sparseArr1[0][1] = Integer.parseInt(split[1]);
            sparseArr1[0][2] = Integer.parseInt(split[2]);
            for (int i = 3; i < split.length; i += 3) {
                sum1++;
                sparseArr1[sum1][0] = Integer.parseInt(split[i]);
                sparseArr1[sum1][1] = Integer.parseInt(split[i + 1]);
                sparseArr1[sum1][2] = Integer.parseInt(split[i + 2]);
            }
            System.out.println("还原后的稀疏数组为：");
            int sparseArr1_length = sparseArr1.length;
            for (int i = 0; i < sparseArr1_length; i++) {
                System.out.printf("%d\t%d\t%d\t\n", sparseArr1[i][0], sparseArr1[i][1], sparseArr1[i][2]);
            }
            System.out.println();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}