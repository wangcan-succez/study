/**
 * 存放文件管理系统的所有API接口和数据结构定义。
 */

/**
 * 控件参数。
 */
interface ComponentArgs {

    /**
     * 控件的dom，通常不需要传递，如果没有传递那么将自动创建一个根dom。
     */
    domBase?: HTMLElement;

    /**
     * 控件的父dom，如果构造时未传递，那么可在构造后调用{@link #setDomParent()}设置。
     */
    domParent?: HTMLElement;

    /**
     * 控件的id，会设置到this.id上，同时也会根据`dontSetDomId`决定是否作用到domBase的id上。
     */
    id?: string;

    /**
     * 设置控件是否可见。
     */
    visible?: boolean;

    /**
     * 设置控件是否可用
     */
    enabled?: boolean;

    /**
     * 根dom上的class，不能是空格分割的多个class，只能是一个class。
     */
    className?: string;
}

/**
 * 表示一个对象是否是可释放的。
 */
interface IDisposable {
	/**
	 * 销毁自己，尽量释放资源，和循环引用，释放监听的事件和创建的dom。
	 */
    dispose(): void;
}

interface Component extends IDisposable {

    /**
     * 此控件的根dom，控件内部可能有多个dom，变量命名都以dom开头，如domText，domArrow。
     */
    domBase: HTMLElement;

	/**
	 * 此控件的父元素，一开始不赋值，在后面build完控件后调用setDomParent赋值。
	 */
    domParent: HTMLElement;

	/**
	 * 控件的id，默认空，构造时可以传递，如btn-submit。
	 */
    id: string;

	/**
	 * @returns 返回控件的根dom。
	 */
    getDomBase(): HTMLElement;

	/**
	 * @returns 返回控件的父dom，可能为空。
	 */
    getDomParent(): HTMLElement;

	/**
	 * 设置将此控件渲染在哪个dom节点下。
	 *
	 * @param [domParent] 表示父dom，为空表示把这个控件从dom树上摘下来。
	 * @param [beforeNode] 表示将控件插入到父dom的哪个子dom前面
	 */
    setDomParent(domParent?: HTMLElement, beforeNode?: Node): void;

    /**
	 * 修改设置显示隐藏的方式。
	 *
	 * `setVisible`函数是控制控件显示隐藏的标准API函数，通常不需要调用`setDisplay`和`setVisibility`函数。
	 *
	 * 默认通过修改display来控制显示隐藏，因为这样性能更好，少数情况下子类可以重载此函数控制具体的实现逻辑。
	 *
	 * @param v true可见/false不可见
	 */
    setVisible(v: boolean): void;

	/**
	 * @returns 返回控件是否可见。
	 */
    isVisible(): boolean;

	/**
	 * 设置控件是否可用，默认是可用的。
	 *
	 * 不可用的控件，会自动增加一个attribute：disabled，并且自动加一个class：sz-disabled。
	 *
	 * @param v
	 */
    setEnabled(v: boolean): void;

	/**
	 * @returns 返回控件是否可用。
	 */
    isEnabled(): boolean;

	/**
	 * 修改控件的className，去掉domBase上原有的className，加上新的
	 */
    setClassName(v: string): void;
}