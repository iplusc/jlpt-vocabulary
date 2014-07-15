//
//  ICDeviceHelper.h
//  jlpt_voc
//
//  Created by guo on 2014/07/15.
//  Copyright (c) 2014年 iplus. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface ICDeviceHelper : NSObject

// iPhoneか
+ (BOOL)isPhone;
// Retinaディスプレイか
+ (BOOL)isRetina;
// 4inch(iPhone5)か
+ (BOOL)is568h;
// 言語環境が日本語か
+ (BOOL)isJapaneseLanguage;

@end
