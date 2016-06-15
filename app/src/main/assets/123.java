try {
                    //add yym 2015-11-10 修改项目名称超过7个字换一行
                    int byteNum = 0;
                    String oldStr = "",newStr = "";
                    oldStr= listSfXms.get(i).getXm();
                    Log.i("str",oldStr);
                    //分割字符串
                    while (oldStr.length()>7){
                        for(int ii = 0;ii<oldStr.length();ii++){
                            int c = (int)oldStr.charAt(ii);
                            if((c>=65&&c<=90)||(c>=97&&c<=122)||(c>48&&c<57)){
                                byteNum = byteNum+1;//西文或者数字
                            }else{
                                byteNum = byteNum+2;//中文
                            }
                            if((byteNum==13)||(byteNum==14)){
                                byteNum = 0;
                                newStr = oldStr.substring(0,ii+1);
                                oldStr = oldStr.substring(ii+1);
                                printerData=byteMerger(printerData, newStr.getBytes("gb18030"));
                                printerData=byteMerger(printerData, "\n".getBytes("gb18030"));
                                Log.i("newStr",newStr);
                                break;
                            }
                        }
                    }
                    if(oldStr.length()>0){
                        Log.i("oldstr",oldStr);
                        printerData=byteMerger(printerData,oldStr.getBytes("gb18030"));
                        printerData=byteMerger(printerData, "\n".getBytes("gb18030"));
                    }
//                    //add yym 2015-11-9 修改项目名称超过7个字换一行
//                    String oldStr = "",newStr = "";
//                    oldStr= listSfXms.get(i).getXm();
//                    while(oldStr.length()>7){
//                        newStr = oldStr.substring(0,7);
//                        oldStr = oldStr.substring(7);
//                        printerData=byteMerger(printerData,newStr.getBytes("gb18030"));
//                        printerData=byteMerger(printerData, "\n".getBytes("gb18030"));
//                    }
//                    if(oldStr.length()>0){
//                        printerData=byteMerger(printerData,oldStr.getBytes("gb18030"));
//                        printerData=byteMerger(printerData, "\n".getBytes("gb18030"));
//                    }
//                    printerData=byteMerger(printerData, listSfXms.get(i).getXm().getBytes("gb18030"));
//                    printerData=byteMerger(printerData, "\n".getBytes("gb18030"));
                    printerData=byteMerger(printerData, djmove);
                    if(type.equals("1")){
                        printerData=byteMerger(printerData, ("-"+listSfXms.get(i).getHsdj()).getBytes("gb18030"));
                    }else  if(type.equals("0")){
                        printerData=byteMerger(printerData, listSfXms.get(i).getHsdj().getBytes("gb18030"));
                    }
                    printerData=byteMerger(printerData, slmove);
                    printerData=byteMerger(printerData, listSfXms.get(i).getSl().getBytes("gb18030"));
                    printerData=byteMerger(printerData, jemove);
                    if(type.equals("1")){
                        printerData=byteMerger(printerData, ("-"+listSfXms.get(i).getHsje()).getBytes("gb18030"));
                    }else if(type.equals("0")){
                        printerData=byteMerger(printerData, listSfXms.get(i).getHsje().getBytes("gb18030"));
                    }
                    printerData=byteMerger(printerData, "\n".getBytes("gb18030"));
                } catch (Exception e) {
                    // TODO: handle exception
                }
